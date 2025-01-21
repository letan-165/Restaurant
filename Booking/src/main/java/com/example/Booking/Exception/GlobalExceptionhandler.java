package com.example.Booking.Exception;

import com.example.Booking.DTO.ApiResponse;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse>handlingException(){
        return ResponseEntity.status(ErrolCode.ERROL_OTHER.getHttpStatus())
                .body(ApiResponse.builder()
                        .code(ErrolCode.ERROL_OTHER.getCode())
                        .message(ErrolCode.ERROL_OTHER.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse>handlingAppException(AppException exception){
        return ResponseEntity.status(exception.getErrolCode().getHttpStatus())
                .body(ApiResponse.builder()
                        .code(exception.getErrolCode().getCode())
                        .message(exception.getErrolCode().getMessage())
                        .build());
    }
    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse>handlingAppException(){
        ErrolCode errolCode = ErrolCode.UNAUTHORIZED;
        return ResponseEntity.status(errolCode.getHttpStatus())
                .body(ApiResponse.builder()
                        .code(errolCode.getCode())
                        .message(errolCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = FeignException.BadRequest.class)
    ResponseEntity<ApiResponse>handlingFeignBadRequest(FeignException.BadRequest exception){
        return ResponseEntity.status(exception.status())
                .body(ApiResponse.builder()
                        .code(456)
                        .message(exception.contentUTF8())
                        .build());
    }

}
