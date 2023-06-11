package com.vodafone.models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentMessage {
    private String codeMessage;
    private int Code;
    public PaymentMessage() {
    }

    public PaymentMessage(String codeMessage, int errorCode) {
        this.codeMessage = codeMessage;
        this.Code = errorCode;
    }

    public String getMessage() {
        return codeMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.codeMessage = errorMessage;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }


}
