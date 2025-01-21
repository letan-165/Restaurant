package com.example.Api_Gateway.Repository;

import com.example.Api_Gateway.DTO.ApiResponse;
import com.example.Api_Gateway.DTO.Request.TokenRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface UserClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<Boolean>> introspect(@RequestBody TokenRequest request);
}

