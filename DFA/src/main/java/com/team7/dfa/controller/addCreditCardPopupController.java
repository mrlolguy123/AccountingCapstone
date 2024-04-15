package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * the controller for addCreditCardPopup.fxml
 */
public class addCreditCardPopupController extends ParentController{

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField cardNameText;
    @FXML
    private TextField cardNumText;
    @FXML
    private TextField cardExpiryText;
    @FXML
    private TextField cardSecText;

    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(addBankPopupController.class.getName());
    }

    /**
     * When confirm is clicked, creates a BankAccount from values in textFields
     * @param event  the captured click event
     */
    @FXML
    protected void confirmClicked(ActionEvent event){
        boolean cardNumberValid = false;
        boolean cardExpiryValid = false;
        boolean cardSecValid = false;
        String cardNum = cardNumText.getText();
        String cardExpiry = cardExpiryText.getText();
        String cardSec = cardSecText.getText();

        if((cardNum.length()>15)&&(cardNum.length()<20)&&(cardNum.matches("[0-9]+"))){
            cardNumberValid=true;
        }
        if((cardExpiry.length()==5)&&(cardExpiry.matches("^(0[1-9]|1[0-2])\\/?([0-9]{2})$"))){
            cardExpiryValid=true;
        }
        if((cardSec.length()==3)&&(cardSec.matches("[0-9]{3}"))){
            cardSecValid=true;
        }
        if(cardNumberValid && cardExpiryValid && cardSecValid) {

            try {
                PreparedStatement insertStatement = con.prepareStatement("INSERT INTO dbo.andrewCardRecord VALUES (?,?,?,?);");
                insertStatement.setString(1, cardNameText.getText());
                insertStatement.setString(2, cardNum);
                insertStatement.setString(3, cardExpiry);
                insertStatement.setString(4, cardSec);
                insertStatement.executeUpdate();
                log.info("Insert statement into dbo.andrewCardRecord in confirmClicked successful.");
            } catch (SQLException e) {
                log.info("Insert statement into dbo.andrewCardRecord in confirmClicked failed");
            }

            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        } else if(cardNumberValid && cardExpiryValid && !cardSecValid){
            throwError("Your Security Code is not Valid.");
        } else if(cardNumberValid && !cardExpiryValid && cardSecValid){
            throwError("Your Expiry Date is not Valid.");
        } else if(!cardNumberValid && cardExpiryValid && cardSecValid){
            throwError("Your Card Number is not Valid.");
        } else if(cardNumberValid && !cardExpiryValid && !cardSecValid){
            throwError("Your Card Expiry Date and your Card Security Code are not Valid.");
        } else if(!cardNumberValid && cardExpiryValid && !cardSecValid){
            throwError("Your Card Number and your Card Security Code are not Valid.");
        } else if(!cardNumberValid && !cardExpiryValid && cardSecValid){
            throwError("Your Card Number and your Card Expiry Date are not Valid.");
        } else if(!cardNumberValid && !cardExpiryValid && !cardSecValid){
            throwError("Your Card Number, Card Expiry Date, and Card Security Code are not Valid.");
        }
    }

    /**
     * closes the window when cancel is clicked
     * @param event the captured click event
     */
    @FXML
    protected void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}