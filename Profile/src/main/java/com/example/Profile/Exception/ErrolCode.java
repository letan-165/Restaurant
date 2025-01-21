package com.example.Profile.Exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public enum ErrolCode {
    ERROL_OTHER(9999,"errol other", HttpStatus.INTERNAL_SERVER_ERROR),
    USERID_NO_EXISTS(6001,"userid no exists", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(6002,"No have permission",HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(6003,"Token no verify",HttpStatus.UNAUTHORIZED),
    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}
