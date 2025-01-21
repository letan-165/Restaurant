package com.example.Menu.Exception;

import com.example.Menu.DTO.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(){
        return ResponseEntity.badRequest()
                .body(ApiResponse.builder()
                        .code(ErrolCode.ORDER_EXCEPTION.getCode())
                        .message(ErrolCode.ORDER_EXCEPTION.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        return ResponseEntity.status(exception.getErrolCode().getHttpStatus())
                .body(ApiResponse.builder()
                        .code(exception.getErrolCode().getCode())
                        .message(exception.getErrolCode().getMessage())
                        .build());
    }
    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(){
        return ResponseEntity.status(ErrolCode.UNAUTHORIZED.getHttpStatus())
                .body(ApiResponse.builder()
                        .code(ErrolCode.UNAUTHORIZED.getCode())
                        .message(ErrolCode.UNAUTHORIZED.getMessage())
                        .build());
    }
}
