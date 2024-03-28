package com.team7.dfa.model;

import java.util.Date;

public class transactions {
    private Date date;
    private int amount;
    private String accountNum;
    private String cardNum;
    private int transID;

    public transactions(){}

    public transactions(Date date, int amount, String accountNum, String cardNum,int transID){
        this.date = date;
        this.amount = amount;
        this.accountNum = accountNum;
        this.cardNum = cardNum;
        this.transID = transID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }
}
