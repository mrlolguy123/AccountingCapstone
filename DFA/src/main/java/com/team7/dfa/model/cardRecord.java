package com.team7.dfa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class cardRecord {

    private String cardName;
    private String cardNum;
    private String cardExpiry;
    private String cardSec;
    private int EmployeeID;

    public cardRecord() {
    }

    public cardRecord(String cardName, String cardNum, String cardExpiry, String cardSec) {
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.cardExpiry = cardExpiry;
        this.cardSec = cardSec;
        this.EmployeeID = 0;
    }

    public cardRecord(String cardName, String cardNum, String cardExpiry, String cardSec, int EmployeeID) {
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.cardExpiry = cardExpiry;
        this.cardSec = cardSec;
        this.EmployeeID = EmployeeID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardSec() {
        return cardSec;
    }

    public void setCardSec(String cardSec) {
        this.cardSec = cardSec;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }
    /*
    @Override
    public String toString() {
        return "cardRecord{" +
                "cardName=" + cardName +
                ", CardNum='" + cardNum + '\'' +
                ", CardExpiry='" + cardExpiry + '\'' +
                ", cardSec=" + cardSec +
                '}';
    }

     */
}