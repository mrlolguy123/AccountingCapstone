package com.team7.dfa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class transactions {
    private Date date;
    private IntegerProperty amount;
    private StringProperty accountNum;
    private StringProperty cardNum;
    private StringProperty transID;

    public transactions(){}

    public transactions(Date date, int amount, String accountNum, String cardNum, String transID){
        this.date = date;
        amountProperty().set(amount);
        accountNumProperty().set(accountNum);
        cardNumProperty().set(cardNum);
        transIDProperty().set(transID);
    }

    public IntegerProperty amountProperty(){
        if(amount == null) amount = new SimpleIntegerProperty(this, "amount");
        return amount;
    }
    public StringProperty accountNumProperty(){
        if(accountNum==null) accountNum = new SimpleStringProperty(this, "accountNum");
        return accountNum;
    }
    public StringProperty cardNumProperty(){
        if(cardNum == null) cardNum = new SimpleStringProperty(this,"cardNum");
        return cardNum;
    }
    public StringProperty transIDProperty(){
        if(transID==null) transID = new SimpleStringProperty(this,"transID");
        return transID;
    }
    public Date getDate(){
        return date;
    }
    public int getAmount(){
        return amountProperty().get();
    }
    public String getAccountNum() {
        return accountNumProperty().get();
    }
    public String getCardNum(){return cardNumProperty().get();}
    public String getTransID(){return transIDProperty().get();}
    public void setDate(Date date){
        this.date = date;
    }
    public void setAmount(int amount){
        this.amountProperty().set(amount);
    }
    public void setAccountNum(String accountNum){
        this.accountNumProperty().set(accountNum);
    }
    public void setCardNum(String cardNum){
        this.cardNumProperty().set(cardNum);
    }
    public void setTransID(String transID){
        this.transIDProperty().set(transID);
    }

}
