package com.example.User.Service;

import com.example.User.DTO.Request.LogoutRequest;
import com.example.User.DTO.Request.TokenRequest;
import com.example.User.DTO.Request.LoginRequest;
import com.example.User.Entity.User;
import com.example.User.Exception.AppException;
import com.example.User.Exception.ErrolCode;
import com.example.User.Mapper.UserMapper;
import com.example.User.Repository.HttpClient.LogoutClient;
import com.example.User.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class AuthService {
    UserRepository userRepository;
    LogoutClient logoutClient;
    UserMapper userMapper;

    @NonFinal
    @Value("${key.value}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${time.valid}")
    Long timeValid;

    @NonFinal
    @Value("${time.refreshable}")
    Long timeRefreshable;

    public String login(LoginRequest request){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = userRepository.findByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new AppException(ErrolCode.PASSWORD_UNVALIBLE);
        }
        return generateToken(user);
    }

    public boolean introspect(TokenRequest request){
            try {
                verifyToken(request.getToken(),false);
            } catch (JOSEException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        return true;
    }

    public String info(){
        var context = SecurityContextHolder.getContext();
        return  context.getAuthentication().getName();
    }

    public boolean logout(TokenRequest request){
        try{
            var verify = verifyToken(request.getToken(),false);
            logoutClient.save(LogoutRequest.builder()
                    .token(verify.getJWTClaimsSet().getJWTID())
                    .expiryTime(verify.getJWTClaimsSet().getExpirationTime())
                    .build());
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public String refresh(TokenRequest request){
        try {
            var verify = verifyToken(request.getToken(),true);
            User user = userRepository.findById(verify.getJWTClaimsSet().getSubject())
                    .orElseThrow(()->new AppException(ErrolCode.USERNAME_NO_EXITS));
            logout(request);
            return generateToken(user);
        } catch (JOSEException | ParseException c) {
            throw new AppException(ErrolCode.TOKEN_REFRESH_FALSE);
        }

    }


    public String generateToken(User user){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getUserID())
                .issuer("letan.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(timeValid,ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add("ROLE_"+user.getRole().getRoleName());
        if (!CollectionUtils.isEmpty(user.getRole().getPermissions())){
            user.getRole().getPermissions().forEach(permission -> stringJoiner.add(permission.getPermissionName()));
        }
        return stringJoiner.toString();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = (isRefresh)
                ?new Date(signedJWT.getJWTClaimsSet().getExpirationTime()
                .toInstant().plus(timeRefreshable,ChronoUnit.HOURS).toEpochMilli())
                :signedJWT.getJWTClaimsSet().getExpirationTime();

        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        var verified = signedJWT.verify(jwsVerifier);

        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrolCode.TOKEN_EXPIRYTIME);
        }
        if(logoutClient.existsById(signedJWT.getJWTClaimsSet().getJWTID()).getResult()){
            throw new AppException(ErrolCode.TOKEN_EXITS);
        }
        return signedJWT;
    }

}
