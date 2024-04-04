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
 * the controller for addBankPopup.fxml
 */
public class addBankPopupController extends ParentController {

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField bankNameText;
    @FXML
    private TextField accountNumText;
    @FXML
    private TextField routingNumText;

    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(addBankPopupController.class.getName());
    }

    /**
     * When confirm is clicked, creates a BankAccount from values in textFields
     * @param event the captured click event
     */
    @FXML
    protected void confirmClicked(ActionEvent event){

        try {
            PreparedStatement insertStatement = con.prepareStatement("INSERT INTO dbo.andrewBankAccounts VALUES (?,?,?);");
            insertStatement.setString(1, bankNameText.getText());
            insertStatement.setString(2, accountNumText.getText());
            insertStatement.setString(3, routingNumText.getText());
            insertStatement.executeUpdate();
        }
        catch(SQLException e){
            log.info("Insert statement into dbo.andrewBankAccounts in confirmClicked failed");
        }

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
        log.info("Insert statement into dbo.andrewBankAccounts in confirmClicked successful.");
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