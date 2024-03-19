package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class addBankPopupController {

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    @FXML
    protected void confirmClicked(ActionEvent event) {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}