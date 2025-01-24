package com.example.Booking.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrolCode {
    ERROL_OTHER(9999, "Errol other", HttpStatus.INTERNAL_SERVER_ERROR),
    BOOKING_NO_EXITS(1001, "Booking no exits",HttpStatus.BAD_REQUEST),
    MENU_NO_EXISTS(1002, "Menu no exits",HttpStatus.BAD_REQUEST),
    MENU_HAD_BOOKING(1003, "Menu had booking",HttpStatus.BAD_REQUEST),
    TABLE_NO_EXISTS(1004, "Table no exits",HttpStatus.BAD_REQUEST),
    TABLE_HAD_BOOKING(1005, "Table had booking",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006,"Token no verify",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007,"No have permission",HttpStatus.FORBIDDEN),
    USER_NO_EXISTS(1008,"User no exists",HttpStatus.BAD_REQUEST),
    INVOICE_NO_EXISTS(1009,"Invoice no exists",HttpStatus.BAD_REQUEST),
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
