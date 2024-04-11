package com.team7.dfa.model;

/**
 * This class is the model for Card Record database entries
 * It contains the attributes for cardRecord
 */
public class cardRecord {

    private String cardName;
    private String cardNum;
    private String cardExpiry;
    private String cardSec;
    private int EmployeeID;

    /**
     * A test constructor excluding employeeID, should not be used for any database interaction
     * @param cardName the optional name give by the user
     * @param cardNum the card number, should be 16 digits plus dividers #### #### #### ####
     * @param cardExpiry the expiration date, should be MM/YY
     * @param cardSec the security code, 3 digits ###
     */
    public cardRecord(String cardName, String cardNum, String cardExpiry, String cardSec) {
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.cardExpiry = cardExpiry;
        this.cardSec = cardSec;
    }

    /**
     * Constructor for uploding entries to the database
     * @param cardName the optional name give by the user
     * @param cardNum the card number, should be 16 digits plus dividers #### #### #### ####
     * @param cardExpiry the expiration date, should be MM/YY
     * @param cardSec the security code, 3 digits ###
     * @param EmployeeID the ID of the employee this card is registered to
     */
    public cardRecord(String cardName, String cardNum, String cardExpiry, String cardSec, int EmployeeID) {
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.cardExpiry = cardExpiry;
        this.cardSec = cardSec;
        this.EmployeeID = EmployeeID;
    }

    /**
     * This method returns the name of the card.
     * @return cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * This method sets the name of the card.
     * @param cardName Name of the card
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * This method returns the card number.
     * @return cardNum
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * This method sets the card number.
     * @param cardNum Card number
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * This method returns the expiry date of the card.
     * @return cardExpiry
     */
    public String getCardExpiry() {
        return cardExpiry;
    }

    /**
     * This method sets the expiry date of the card.
     * @param cardExpiry Expiry date of the card
     */
    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    /**
     * This method returns the security code of the card.
     * @return cardSec
     */
    public String getCardSec() {
        return cardSec;
    }

    /**
     * This method sets the security code of the card.
     * @param cardSec Security code of the card
     */
    public void setCardSec(String cardSec) {
        this.cardSec = cardSec;
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
     * @param employeeID Employee ID
     */
    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }
}