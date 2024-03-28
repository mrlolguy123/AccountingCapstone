package com.team7.dfa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class cardRecord {

    private StringProperty cardName;
    private StringProperty cardNum;
    private StringProperty cardExpiry;
    private StringProperty cardSec;
    private IntegerProperty EmployeeID;

    public cardRecord() {
    }

    public cardRecord(String cardName, String cardNum, String cardExpiry, String cardSec) {
        cardNameProperty().set(cardName);
        cardNumProperty().set(cardNum);
        cardExpiryProperty().set(cardExpiry);
        cardSecProperty().set(cardSec);
        EmployeeIDProperty().set(0);
    }

    public cardRecord(String cardName, String cardNum, String cardExpiry, String cardSec, int EmployeeID) {
        cardNameProperty().set(cardName);
        cardNumProperty().set(cardNum);
        cardExpiryProperty().set(cardExpiry);
        cardSecProperty().set(cardSec);
        EmployeeIDProperty().set(EmployeeID);
    }
    public StringProperty cardNameProperty(){
        if (cardName == null) cardName = new SimpleStringProperty(this, "cardName");
        return cardName;
    }

    public StringProperty cardNumProperty(){
        if (cardNum == null) cardNum = new SimpleStringProperty(this, "cardNum");
        return cardNum;
    }

    public StringProperty cardExpiryProperty(){
        if (cardExpiry == null) cardExpiry = new SimpleStringProperty(this, "cardExpiry");
        return cardExpiry;
    }

    public StringProperty cardSecProperty(){
        if (cardSec == null) cardSec = new SimpleStringProperty(this, "cardSec");
        return cardSec;
    }

    public IntegerProperty EmployeeIDProperty(){
        if (EmployeeID == null) EmployeeID = new SimpleIntegerProperty(this, "EmployeeID");
        return EmployeeID;
    }


    public String getCardName() {
        return cardNameProperty().get();
    }

    public String getCardNum() {
        return cardNumProperty().get();
    }

    public String getCardExpiry() {
        return cardExpiryProperty().get();
    }

    public String getCardSec() {
        return cardSecProperty().get();
    }

    public int getEmployeeID() {
        return EmployeeIDProperty().get();
    }

    public void setCardName(String cardName) {
        this.cardNameProperty().set(cardName);
    }

    public void setCardNum(String cardNum) {
        this.cardNumProperty().set(cardNum);
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiryProperty().set(cardExpiry);
    }

    public void setCardSec(String cardSec) {
        this.cardSecProperty().set(cardSec);
    }

    public void setEmployeeID(int EmployeeID){
        this.EmployeeIDProperty().set(EmployeeID);
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