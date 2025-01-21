package com.example.Invoice.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrolCode {
    ERROL_OTHER(9999, "Errol other", HttpStatus.INTERNAL_SERVER_ERROR),
    INVOICE_NO_EXITS(5001, "Invoice no exits",HttpStatus.BAD_REQUEST),
    MENU_NO_EXISTS(4002, "Menu no exits",HttpStatus.BAD_REQUEST),
    MENU_HAD_BOOKING(4003, "Menu had booking",HttpStatus.BAD_REQUEST),
    TABLE_NO_EXISTS(4002, "Table no exits",HttpStatus.BAD_REQUEST),
    TABLE_HAD_BOOKING(4005, "Table had booking",HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(4006,"No have permission",HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(4007,"Token no verify",HttpStatus.UNAUTHORIZED),
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
