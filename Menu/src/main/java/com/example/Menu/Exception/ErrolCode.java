package com.example.Menu.Exception;

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
    ORDER_EXCEPTION(9999, "Errol Other", HttpStatus.INTERNAL_SERVER_ERROR),
    NAME_ITEM_NO_EXITS(2000, "Item name hadn't exits", HttpStatus.BAD_REQUEST),
    NAME_ITEM_EXITS(2001, "Item name had exits", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(2002,"you don't have permission",HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(2003,"token no verify",HttpStatus.UNAUTHORIZED),
    ;
     int code;
     String message;
     HttpStatusCode httpStatus;
}
