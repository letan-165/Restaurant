package com.example.Profile.Exception;

import com.example.Profile.DTO.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(){
        ErrolCode errolCode = ErrolCode.ERROL_OTHER;
        return ResponseEntity.status(errolCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(errolCode.getCode())
                        .message(errolCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrolCode errolCode = exception.getErrolCode();
        return ResponseEntity.status(errolCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(errolCode.getCode())
                        .message(errolCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handlingAuthorizationDeniedException(){
        ErrolCode errolCode = ErrolCode.UNAUTHORIZED;
        return ResponseEntity.status(errolCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(errolCode.getCode())
                        .message(errolCode.getMessage())
                        .build());
    }

}
