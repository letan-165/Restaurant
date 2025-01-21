package com.example.Api_Gateway.Config;

import com.example.Api_Gateway.DTO.ApiResponse;
import com.example.Api_Gateway.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    UserService userService;
    ObjectMapper objectMapper;

    @NonFinal
    String[] publicEndpoints = {"/user_service/.*"
            ,"/user_service/user/public(/.*)?"
            ,"/menu_service(/.*)?","/table_service(/.*)?"
            , "/booking_service(/.*)?","/invoice_service(/.*)?","/profile_service(/.*)?"};

    @NonFinal
    @Value("${app.api-prefix}")
    String apiPrefix;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(isPublicEndpoint(exchange.getRequest())){
            return chain.filter(exchange);
        }

        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader)){
            return unauthenticated(exchange.getResponse(),"Header empty");
        }

        String token = authHeader.getFirst().replace("Bearer","");

        return userService.introspect(token).flatMap(s -> {
            if(s.getResult()){
                return chain.filter(exchange);
            }else {
                return unauthenticated(exchange.getResponse(),"Token no verify");
            }
        }).onErrorResume(throwable -> unauthenticated(exchange.getResponse(),"Other errol"));
    }

    boolean isPublicEndpoint(ServerHttpRequest request){
        return Arrays.stream(publicEndpoints).anyMatch(s -> request.getURI().getPath().matches(apiPrefix + s));
    }


    Mono<Void> unauthenticated(ServerHttpResponse response,String message){
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(1234)
                .message(message)
                .build();
        String body = null;

        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
