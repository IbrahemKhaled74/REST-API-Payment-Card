package com.vodafone.models;

public class PaymentService {
    public String cardNumber;
    public Double amount;
    public int cvv;
    public String month;
    public String year;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getCvv() {
        return cvv;
    }

    public PaymentService() {
    }

    public PaymentService(String cardNumber, Double amount, int cvv, String month, String year) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.cvv = cvv;
        this.month = month;
        this.year = year;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
