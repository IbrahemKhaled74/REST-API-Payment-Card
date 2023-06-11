package com.vodafone.exception;

import jakarta.ws.rs.ext.Provider;

@Provider

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message){

        super(message);
    }

}
