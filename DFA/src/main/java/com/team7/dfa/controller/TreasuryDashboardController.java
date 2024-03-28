package com.team7.dfa.controller;

import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.model.InvoiceModel;
import com.team7.dfa.model.bankAccount;
import com.team7.dfa.model.cardRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class TreasuryDashboardController extends ParentController {
    public static bankAccount selectedAccount;
    @FXML
    private TableColumn<cardRecord, String> cardNameCol;
    @FXML
    private TableColumn<cardRecord, String> cardNumCol;
    @FXML
    private TableColumn<cardRecord, String> cardExpiryCol;
    @FXML
    private TableColumn<cardRecord, String> cardSecCol;
    @FXML
    private TableView<cardRecord> cardTable;
    @FXML
    private TableView<bankAccount> bankTable;
    @FXML
    private TableColumn<bankAccount, String> bankNameCol;
    @FXML
    private TableColumn<bankAccount, String> accountNumCol;
    @FXML
    private TableColumn<bankAccount, String> routingNumCol;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button addBankButton;
    @FXML
    private Button addCreditCardButton;
    @FXML
    private Button importStatementButton;

    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(TreasuryDashboardController.class.getName());
    }

    @FXML
    protected void logoutClicked(ActionEvent event)
    {
        System.exit(0);
    }

    // opens a popup that allows the suer to enter a new Credit Card into the database
    // medium is addCreditCardPopup
    @FXML
    protected void addCreditCardClicked(ActionEvent event) throws IOException {
        Stage creditCardWindow = new Stage();
        creditCardWindow.setTitle("Add New Credit Card");
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addCreditCardPopup.fxml"));
        creditCardWindow.setScene(new Scene(loader.load()));
        creditCardWindow.show();
    }

    // opens a popup that allows the user to enter a new bank account into the database
    // medium is addBankPopup
    @FXML
    protected void addBankClicked(ActionEvent event) throws IOException{
        Stage bankWindow = new Stage();
        bankWindow.setTitle("Add New Bank Account");
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addBankPopup.fxml"));
        bankWindow.setScene(new Scene(loader.load()));
        bankWindow.show();
    }

    // allows you to enter a statement
    @FXML
    protected void importStatementClicked(ActionEvent event) throws IOException{
        //do nothing rn
    }

    // updates card table when acordian is closed/opened
    @FXML
    protected void cardMousePressed() throws IOException, SQLException {
        ObservableList<cardRecord> records = getRecords(con);
        try {
            cardNameCol.setCellValueFactory(new PropertyValueFactory<cardRecord, String>("cardName"));
            cardNumCol.setCellValueFactory(new PropertyValueFactory<cardRecord, String>("cardNum"));
            cardExpiryCol.setCellValueFactory(new PropertyValueFactory<cardRecord, String>("cardExpiry"));
            cardSecCol.setCellValueFactory(new PropertyValueFactory<cardRecord, String>("cardSec"));

            cardTable.setItems(records);
        } catch (Exception e) {
            log.info("Could not fill Card Table");
        }
    }

    // updates bank table when acordian is closed/opened
    @FXML
    protected void bankMousePressed() throws SQLException{
        ObservableList<bankAccount> accounts = getAccounts(con);

        try {
            bankNameCol.setCellValueFactory(new PropertyValueFactory<>("bankName"));
            accountNumCol.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
            routingNumCol.setCellValueFactory(new PropertyValueFactory<>("routingNum"));

            bankTable.setItems(accounts);
        } catch(Exception e){
            log.info("Could not fill Bank Table");
        }
    }

    // opens the transactions linked to a bank account upon being double clicked
    @FXML
    protected void itemClickedBank(Event event) throws IOException {
        if(event.getEventType().getName().equals("MOUSE_CLICKED"))
        {
            if(((javafx.scene.input.MouseEvent) event).getClickCount() == 2)
            {
                Stage creditCardWindow = new Stage();
                creditCardWindow.setTitle("Bank Account Transaction List");
                FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("bankAccountTransactions.fxml"));
                creditCardWindow.setScene(new Scene(loader.load()));

                selectedAccount = bankTable.getSelectionModel().getSelectedItem();
                creditCardWindow.show();
            }
        }


    }


    // opens the transactions linked to a credit card upon being double clicked
    @FXML
    protected void itemClickedCard(Event event) throws IOException{
        if(event.getEventType().getName().equals("MOUSE_CLICKED")) {
            if (((javafx.scene.input.MouseEvent) event).getClickCount() == 2) {
                Stage creditCardWindow = new Stage();
                creditCardWindow.setTitle("Credit Card Transaction List");
                FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("cardTransactions.fxml"));
                creditCardWindow.setScene(new Scene(loader.load()));

                Object item = cardTable.getSelectionModel().selectedItemProperty().get();
                creditCardWindow.show();
            }
        }
    }

    protected ObservableList<cardRecord> getRecords(Connection connection) throws SQLException{
        ResultSet rs = readDataRecords(connection);
        ObservableList<cardRecord> oL = FXCollections.observableArrayList();
        while (rs.next()){
            cardRecord temp = new cardRecord(rs.getString("CardName"),
                    rs.getString("CardNum"),
                    rs.getString("CardExpiry"),
                    rs.getString("CardSec"));
            oL.add(temp);
        }
        return oL;
    }

    protected ResultSet readDataRecords(Connection connection) throws SQLException{
        Statement stmt = connection.createStatement();
        String SQL = "SELECT * FROM dbo.andrewCardRecord;";
        ResultSet rs = stmt.executeQuery(SQL);
        return rs;
    }

    protected ObservableList<bankAccount> getAccounts(Connection connection) throws SQLException{
        ResultSet rs = readDataAccounts(connection);
        ObservableList<bankAccount> oL = FXCollections.observableArrayList();
        while(rs.next()){
            bankAccount temp = new bankAccount(rs.getString("bankName"),
                    rs.getString("accountNum"),
                    rs.getString("routeNum"));
            oL.add(temp);
        }
        return oL;
    }

    protected ResultSet readDataAccounts(Connection connection) throws SQLException{
        Statement stmt = connection.createStatement();
        String SQL = "Select * FROM dbo.andrewBankAccounts;";
        ResultSet rs = stmt.executeQuery(SQL);
        return rs;
    }
}
