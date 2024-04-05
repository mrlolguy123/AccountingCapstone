package com.team7.dfa.model;

public class bankAccount {
    private String bankName;
    private String accountNum;
    private String routingNum;
    private int EmployeeID;

    public bankAccount(String bankName, String accountNum, String routingNum, int employeeID) {
        this.bankName = bankName;
        this.accountNum = accountNum;
        this.routingNum = routingNum;
        EmployeeID = employeeID;
    }

    public bankAccount(String bankName, String accountNum, String routingNum) {
        this.bankName = bankName;
        this.accountNum = accountNum;
        this.routingNum = routingNum;
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

