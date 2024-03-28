package com.team7.dfa.model;


public class bankAccount {
    private String bankName;
    private String accountNum;
    private String routingNum;
    private int EmployeeID;


    public bankAccount(){
    }

    public bankAccount(String bankName, String accountName, String routingNum){
        this.bankName=bankName;
        this.accountNum=accountName;
        this.routingNum=routingNum;
        this.EmployeeID=0;
    }
    public bankAccount(String bankName, String accountName, String routingNum, int EmployeeID){
        this.bankName=bankName;
        this.accountNum=accountName;
        this.routingNum=routingNum;
        this.EmployeeID=EmployeeID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getRoutingNum() {
        return routingNum;
    }

    public void setRoutingNum(String routingNum) {
        this.routingNum = routingNum;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }
}

