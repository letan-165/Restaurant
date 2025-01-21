package com.example.Booking.Exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{

    private ErrolCode errolCode;

    public AppException(ErrolCode errolCode){
        super(errolCode.getMessage());
        this.errolCode = errolCode;
    }

}
