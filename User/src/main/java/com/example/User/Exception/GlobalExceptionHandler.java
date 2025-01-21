package com.example.User.Exception;

import com.example.User.DTO.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(Exception exception){
        ApiResponse apiResponse = ApiResponse.builder()
                .code(ErrolCode.OTHER_ERROL.getCode())
                .message(ErrolCode.OTHER_ERROL.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrolCode errolCode = exception.getErrolCode();
        ApiResponse apiResponse = ApiResponse.builder()
                .code(errolCode.getCode())
                .message(errolCode.getMessage())
                .build();
        return ResponseEntity.status(errolCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AuthorizationDeniedException exception){
        ErrolCode errolCode = ErrolCode.UNAUTHORIZED;
        return ResponseEntity.status(errolCode.getStatusCode()).body(ApiResponse.builder()
                .code(errolCode.getCode())
                .message(errolCode.getMessage())
                .build());
    }


}
