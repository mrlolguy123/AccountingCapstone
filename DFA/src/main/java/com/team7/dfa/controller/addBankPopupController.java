package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

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

    @FXML
    protected void confirmClicked(ActionEvent event) throws SQLException {

        log.info("Reading entry");
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO dbo.andrewBankAccounts VALUES (?,?,?);");
        insertStatement.setString(1,bankNameText.getText());
        insertStatement.setString(2,accountNumText.getText());
        insertStatement.setString(3,routingNumText.getText());
        insertStatement.executeUpdate();
        log.info("Updating database");

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}