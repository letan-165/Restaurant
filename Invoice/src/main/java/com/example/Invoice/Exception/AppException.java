package com.example.Invoice.Exception;

import com.example.Invoice.Exception.ErrolCode;
import lombok.Data;

@Data
public class AppException extends RuntimeException{

    private ErrolCode errolCode;

    public AppException(ErrolCode errolCode){
        super(errolCode.getMessage());
        this.errolCode = errolCode;
    }

}
