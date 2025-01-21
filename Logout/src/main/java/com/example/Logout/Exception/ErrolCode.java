package com.example.Logout.Exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public enum ErrolCode {
    ORDER_ERROL(1000,"other errol", HttpStatus.INTERNAL_SERVER_ERROR),
    TOKEN_EXITS(5001,"token exits",HttpStatus.BAD_REQUEST),
    TOKEN_NO_EXITS(5001,"token not exits",HttpStatus.BAD_REQUEST),

    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
