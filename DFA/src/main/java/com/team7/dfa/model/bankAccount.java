package com.team7.dfa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class bankAccount {
    private StringProperty bankName;
    private StringProperty accountNum;
    private StringProperty routingNum;
    private IntegerProperty EmployeeID;


    public bankAccount(){
    }

    public bankAccount(String bankName, String accountName, String routingNum){
        bankNameProperty().set(bankName);
        accountNumProperty().set(accountName);
        routeNumProperty().set(routingNum);
        EmployeeIDProperty().set(0);
    }
    public bankAccount(String bankName, String accountName, String routingNum, int EmployeeID){
        bankNameProperty().set(bankName);
        accountNumProperty().set(accountName);
        routeNumProperty().set(routingNum);
        EmployeeIDProperty().set(EmployeeID);
    }
    public StringProperty bankNameProperty(){
        if (bankName == null) bankName = new SimpleStringProperty(this, "bankName");
        return bankName;
    }
    public StringProperty accountNumProperty(){
        if(accountNum == null) accountNum = new SimpleStringProperty(this, "accountNum");
        return accountNum;
    }
    public StringProperty routeNumProperty(){
        if(routingNum==null) routingNum = new SimpleStringProperty(this,"routingNum");
        return routingNum;
    }

    public IntegerProperty EmployeeIDProperty(){
        if (EmployeeID == null) EmployeeID = new SimpleIntegerProperty(this, "EmployeeID");
        return EmployeeID;
    }


    public String getBankName() {
        return bankNameProperty().get();
    }

    public void setBankName(String bankName) {
        this.bankNameProperty().set(bankName);
    }

    public String getAccountNum() {
        return accountNumProperty().get();
    }

    public void setAccountNum(String accountNum) {
        this.accountNumProperty().set(accountNum);
    }

    public String getRoutingNum() {
        return routeNumProperty().get();
    }

    public void setRoutingNum(String routingNum) {
        this.routeNumProperty().set(routingNum);
    }
    public int getEmployeeID() {
        return EmployeeIDProperty().get();
    }
    public void setEmployeeID(int EmployeeID){
        this.EmployeeIDProperty().set(EmployeeID);
    }
}

