package com.vodafone.exception;

public class CardNumberNotFoundException extends RuntimeException {

    public CardNumberNotFoundException(String message){

        super(message);
    }

}
