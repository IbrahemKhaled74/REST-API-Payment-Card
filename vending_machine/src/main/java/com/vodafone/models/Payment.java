package com.vodafone.models;

import java.sql.Date;

public class Payment {

    public double amount;
    public String cardNumber;
    private Date expireDate;
    public int cvv;



    public Payment(double balance, String cardNumber, Date expireDate, int cvv) {
        this.amount = balance;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvv = cvv;
    }

    public Payment(double balance, String cardNumber) {
        this.amount = balance;
        this.cardNumber = cardNumber;
    }

    public Payment(double balance, int cvv, String cardNumber) {
        this.amount = balance;
        this.cvv = cvv;
        this.cardNumber = cardNumber;
    }

    public Payment() {
    }


    public Date getExpireDate() {
        return expireDate;
    }

    public int getCvv() {
        return cvv;
    }


    public String getCardNumber() {
        return this.cardNumber;
    }

    public double getAmount() {
        return this.amount;
    }
}
