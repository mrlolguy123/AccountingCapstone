package com.team7.dfa.controller;

import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.db.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

/**
 * This class is the parent controller for all the other controllers.
 * It contains the methods that are common to all the controllers.
 * It is responsible for handling the navigation between the different views.
 */
public class ParentController {
    DatabaseConnector db = new DatabaseConnector();
    Connection con = db.connect();

    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(addBankPopupController.class.getName());
    }

    /**
     * This method is called when the user clicks the logout button on the sidebar.
     * It closes the application.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void logoutClicked(ActionEvent event) {
        System.exit(0);
    }

    /**
     * This method is called when the user clicks the home button on the sidebar.
     * It loads the homepage view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void homeClicked(ActionEvent event) throws IOException {
        loadFXML("homepage.fxml", event);
    }

    /**
     * This method is called when the user clicks the account button on the sidebar.
     * It loads the account view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void accountClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Accounting");
        alert.setHeaderText("Under Construction...");
        alert.setContentText("This feature is not currently available. Please check back later.");
        alert.showAndWait();
    }

    /**
     * This method is called when the user clicks the invoicing button on the sidebar.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void invoiceClicked(ActionEvent event) throws IOException {
        loadFXML("invoicing.fxml", event);
    }

    /**
     * This method is called when the user clicks the inventory button on the sidebar.
     * It loads the inventory view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void treasuryClicked(ActionEvent event) throws IOException {
        loadFXML("TreasuryDashboard.fxml", event);
    }

    /**
     * This method is called when the user clicks the payroll button on the sidebar.
     * It loads the payroll view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void payrollClicked(ActionEvent event)    throws IOException {
        loadFXML("Payroll.fxml", event);
    }

    /**
     * This method is called when the user clicks any of the sidebar buttons.
     * It loads the FXML view that corresponds to the button clicked.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    public void loadFXML(String link, ActionEvent event) throws IOException {
        db.disconnect();

        Node source = (Node) event.getSource();
        Stage previousStage = (Stage) source.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource(link));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("DFA");
        newStage.setMinHeight(900);
        newStage.setMinWidth(1600);

        previousStage.close();

        newStage.show();
    }

    protected void throwError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
