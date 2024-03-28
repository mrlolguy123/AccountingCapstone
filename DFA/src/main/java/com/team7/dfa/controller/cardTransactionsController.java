package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class cardTransactionsController extends ParentController{

    @FXML
    private Button refreshButton;
    @FXML
    private TableColumn transIDCol;
    @FXML
    private TableColumn cardNumCol;
    @FXML
    private TableColumn dateCol;
    @FXML
    private TableColumn amountCol;
    @FXML
    private Button addButton;
    @FXML
    private Button closeButton;
    @FXML
    private TableView cardTransTable;

    // closes the frame
    @FXML
    protected void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    // refreshes the table with current data
    @FXML
    protected void refreshTable(){
        //String account = TreasuryDashboardController.selectedAccount.getCardNum();
    }

    // adds a new transaction linked to the card
    @FXML
    protected void addTransaction() {
        // not implemented
    }


}
