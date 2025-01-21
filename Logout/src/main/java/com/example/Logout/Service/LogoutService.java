package com.example.Logout.Service;

import com.example.Logout.DTO.Request.ExpiryTimeRequest;
import com.example.Logout.DTO.Request.LogoutRequest;
import com.example.Logout.DTO.Response.LogoutRespone;
import com.example.Logout.Entity.Logout;
import com.example.Logout.Exception.AppException;
import com.example.Logout.Exception.ErrolCode;
import com.example.Logout.Mapper.LogoutMapper;
import com.example.Logout.Repository.LogoutRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class LogoutService {
    LogoutRepository logoutRepository;
    LogoutMapper logoutMapper;

    public List<Logout> findAll(){
        return logoutRepository.findAll();
    }

    public boolean save(LogoutRequest request){
        if(logoutRepository.existsById(request.getToken())){
            throw new AppException(ErrolCode.TOKEN_EXITS);
        }
        logoutRepository.save(logoutMapper.toLogout(request));
        return true;
    }

    public boolean delete(String token){
        if(!logoutRepository.existsById(token)){
            throw new AppException(ErrolCode.TOKEN_NO_EXITS);
        }
        logoutRepository.deleteById(token);
        return true;
    }

    public LogoutRespone findByID(String token){
        if(!logoutRepository.existsById(token)){
            throw new AppException(ErrolCode.TOKEN_NO_EXITS);
        }
        return logoutMapper.toLogoutResponse(logoutRepository.findById(token)
                .orElseThrow(()-> new AppException(ErrolCode.TOKEN_NO_EXITS)));
    }

    public boolean update(String token, ExpiryTimeRequest request){
        if(!logoutRepository.existsById(token)){
            throw new AppException(ErrolCode.TOKEN_NO_EXITS);
        }
        logoutRepository.save(logoutMapper.toLogout(LogoutRequest.builder()
                .token(token)
                .expiryTime(request.getExpiryTime()).build()));
        return true;
    }

    public boolean existsById(String token){
        return logoutRepository.existsById(token);
    }
}
