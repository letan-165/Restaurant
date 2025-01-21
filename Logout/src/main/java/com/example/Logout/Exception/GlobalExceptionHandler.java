package com.example.Logout.Exception;

import com.example.Logout.DTO.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(){
        ErrolCode errolCode = ErrolCode.ORDER_ERROL;
        return ResponseEntity.status(errolCode.getHttpStatusCode()).body(
                ApiResponse.builder()
                        .code(errolCode.getCode())
                        .message(errolCode.getMessage())
                        .build());
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException appException){
        return ResponseEntity.status(appException.getErrolCode().getHttpStatusCode()).body(
                ApiResponse.builder()
                        .code(appException.getErrolCode().getCode())
                        .message(appException.getErrolCode().getMessage())
                        .build());
    }


}
