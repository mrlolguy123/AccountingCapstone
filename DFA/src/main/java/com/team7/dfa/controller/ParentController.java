package com.team7.dfa.controller;

import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.db.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class ParentController {
    DatabaseConnector db = new DatabaseConnector();
    Connection con = db.connect();
    @FXML
    protected void logoutClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void homeClicked(ActionEvent event) throws IOException {
        loadFXML("homepage.fxml", event);

    }

    @FXML
    protected void accountClicked(ActionEvent event) throws IOException {
        // not implemented
    }

    @FXML
    protected void invoiceClicked(ActionEvent event) throws IOException {
        loadFXML("invoicing.fxml", event);
    }

    @FXML
    protected void treasuryClicked(ActionEvent event) throws IOException {
        loadFXML("TreasuryDashboard.fxml", event);
    }

    @FXML
    protected void payrollClicked(ActionEvent event) throws IOException {
        loadFXML("Payroll.fxml", event);
    }

    public void loadFXML(String link, ActionEvent event) throws IOException {
        db.disconnect();

        Node source = (Node) event.getSource();
        Stage previousStage = (Stage) source.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource(link));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("DFA");

        previousStage.close();

        newStage.show();
    }
}
