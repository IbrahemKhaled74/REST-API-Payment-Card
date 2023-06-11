package com.vodafone.exception;

import jakarta.ws.rs.ext.Provider;

@Provider

public class InvalidCardNumberException extends RuntimeException {

    public InvalidCardNumberException(String message){

        super(message);
    }
}
