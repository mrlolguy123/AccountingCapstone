package com.team7.dfa.model;

import java.sql.Date;

/**
 * This class is the model for transactions database entires
 * It contains the attributes for transactions
 */
public class transactions {
    private Date date;
    private int amount;
    private String accountNum;
    private String cardNum;
    private int transID;

    /**
     * A test constructor that has a set transactionID, do not use for interacting with database
     * @param date the date of the transaction, uses SQL date YYYY-MM-DD
     * @param amount the amount of money on the transaction
     * @param accountNum the account the transaction is tied to (default=null)
     * @param cardNum the card the transaction is tied to (default=null)
     */
    public transactions(Date date, int amount, String accountNum, String cardNum) {
        this.date = date;
        this.amount = amount;
        this.accountNum = accountNum;
        this.cardNum = cardNum;
        this.transID=0;
    }

    /**
     * Constructor for uploading entries to the database
     * @param date the date of the transaction, uses SQL date YYYY-MM-DD
     * @param amount the amount of money on the transaction
     * @param accountNum the account the transaction is tied to (default=null)
     * @param cardNum the card the transaction is tied to (default=null)
     * @param transID which number transaction this is
     */
    public transactions(Date date, int amount, String accountNum, String cardNum, int transID) {
        this.date = date;
        this.amount = amount;
        this.accountNum = accountNum;
        this.cardNum = cardNum;
        this.transID = transID;
    }

    /**
     * This method returns the date of the transaction.
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method sets the date of the transaction.
     * @param date SQL date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method returns the amount of the transaction.
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * This method sets the amount of the transaction.
     * @param amount Amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * This method returns the account number of the transaction.
     * @return accountNum
     */
    public String getAccountNum() {
        return accountNum;
    }

    /**
     * This method sets the account number of the transaction.
     * @param accountNum Account number
     */
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * This method returns the card number of the transaction.
     * @return cardNum
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * This method sets the card number of the transaction.
     * @param cardNum Card number
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * This method returns the transaction ID.
     * @return transID
     */
    public int getTransID() {
        return transID;
    }

    /**
     * This method sets the transaction ID.
     * @param transID Transaction ID
     */
    public void setTransID(int transID) {
        this.transID = transID;
    }
}
