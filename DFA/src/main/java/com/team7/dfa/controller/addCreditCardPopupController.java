package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class addCreditCardPopupController {

    @FXML private javafx.scene.control.Button closeButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    @FXML
    protected void confirmClicked(ActionEvent Event) {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void cancelClicked(ActionEvent Event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}