package com.example.Table.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrolCode {
    ERROL_OTHER(9999, "Errol other", HttpStatus.INTERNAL_SERVER_ERROR),
    NAME_EXITS(3001,"Name table had exits ",HttpStatus.BAD_REQUEST),
    NAME_NO_EXITS(3001,"Name table hadn't exits ",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1002,"Token no verify",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1003,"No have permisson",HttpStatus.FORBIDDEN),
    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrolCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
