package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

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

    // adds a new Credit Card upon being pressed
    @FXML
    protected void confirmClicked(ActionEvent Event) throws SQLException {

        log.info("Reading entry");
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO dbo.andrewCardRecord VALUES (?,?,?,?);");
        insertStatement.setString(1,cardNameText.getText());
        insertStatement.setString(2,cardNumText.getText());
        insertStatement.setString(3,cardExpiryText.getText());
        insertStatement.setString(4,cardSecText.getText());
        insertStatement.executeUpdate();
        log.info("Updating database");

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    // closes the frame
    @FXML
    protected void cancelClicked(ActionEvent Event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}