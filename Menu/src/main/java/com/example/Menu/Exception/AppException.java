package com.example.Menu.Exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{
    ErrolCode errolCode;

    public AppException(ErrolCode errolCode){
        super(errolCode.getMessage());
        this.errolCode = errolCode;
    }

}
