package com.team7.dfa.model;

/**
 * This class is the model for Bank Account database entries
 * It contains the attributes for bankAccount
 */
public class bankAccount {
    private String bankName;
    private String accountNum;
    private String routingNum;
    private int EmployeeID;

    /**
     * Constructor for uploading entires to the database
     * @param bankName the optional name given by the user
     * @param accountNum the account number, should be 8-12 digits ############
     * @param routingNum the routing number, should be 9 digits #########
     * @param employeeID the ID of the employee this account is registered to
     */
    public bankAccount(String bankName, String accountNum, String routingNum, int employeeID) {
        this.bankName = bankName;
        this.accountNum = accountNum;
        this.routingNum = routingNum;
        EmployeeID = employeeID;
    }
 /**
  * A test constructor excluding employeeID, should not be used for any database interaction
  * @param bankName the optional name given by the user
  * @param accountNum the account number, should be 8-12 digits
  * @param routingNum the routing number, should be 9 digits
  */
    public bankAccount(String bankName, String accountNum, String routingNum) {
        this.bankName = bankName;
        this.accountNum = accountNum;
        this.routingNum = routingNum;
    }

    /**
     * This method returns the name of the bank.
     * @return bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * This method sets the name of the bank.
     * @param bankName name of the bank
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * This method returns the account number.
     * @return accountNum
     */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * This method sets the account number.
     * @param accountNum account number
     */
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * This method returns the routing number.
     * @return routingNum
     */
    public String getRoutingNum() {
        return routingNum;
    }

    /**
     * This method sets the routing number.
     * @param routingNum routing number
     */
    public void setRoutingNum(String routingNum) {
        this.routingNum = routingNum;
    }

    /**
     * This method returns the employee ID.
     * @return EmployeeID
     */
    public int getEmployeeID() {
        return EmployeeID;
    }

    /**
     * This method sets the employee ID.
     * @param employeeID employee ID
     */
    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }
}

