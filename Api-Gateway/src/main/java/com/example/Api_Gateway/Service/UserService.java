package com.example.Api_Gateway.Service;

import com.example.Api_Gateway.DTO.ApiResponse;
import com.example.Api_Gateway.DTO.Request.TokenRequest;
import com.example.Api_Gateway.Repository.UserClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserClient userClient;

    public Mono<ApiResponse<Boolean>>introspect(String token){
        return userClient.introspect(TokenRequest.builder()
                        .token(token)
                .build());
    }


}
