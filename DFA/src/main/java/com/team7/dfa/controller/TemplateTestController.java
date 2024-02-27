package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TemplateTestController {
    @FXML
    private Button logoutButton;

    @FXML
    protected void logoutClicked(ActionEvent event)
    {
        System.exit(0);
    }
}