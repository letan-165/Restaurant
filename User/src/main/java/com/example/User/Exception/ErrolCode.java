package com.example.User.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.net.http.HttpHeaders;

@Getter
public enum ErrolCode {

    OTHER_ERROL(1001, "New Errol", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1002,"Token no verify",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1003,"No have permisson",HttpStatus.FORBIDDEN),
    USERNAME_EXITS(1004,"Username exited on system", HttpStatus.BAD_REQUEST),
    USERNAME_NO_EXITS(1005,"Username not exited on system", HttpStatus.BAD_REQUEST),
    ROLE_NAME_EXITS(1006,"Role name exited on system", HttpStatus.BAD_REQUEST),
    ROLE_NAME_NO_EXITS(1007,"Role name not exited on system", HttpStatus.BAD_REQUEST),
    PASSWORD_UNVALIBLE(1008,"Password false", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRYTIME(1009,"Token expiry time", HttpStatus.BAD_REQUEST),
    TOKEN_EXITS(1010,"Token exits system", HttpStatus.BAD_REQUEST),
    TOKEN_REFRESH_FALSE(1011,"Token refresh false", HttpStatus.BAD_REQUEST),
    PERMISSION_NAME_EXITS(10012," Permission name exited on system", HttpStatus.BAD_REQUEST),
    PERMISSION_NAME_NO_EXITS(10013," Permission name not exited on system", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrolCode(int code, String message, HttpStatusCode statusCode){
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}