package com.team7.dfa.controller;

import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.model.bankAccount;
import com.team7.dfa.model.cardRecord;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

/**
 * the controller for TreasuryDashboard.fxml
 */
public class TreasuryDashboardController extends ParentController {
    public static bankAccount selectedAccount;
    public static cardRecord selectedCard;
    @FXML
    private TableColumn<cardRecord, String> cardNameCol;
    @FXML
    private TableColumn<cardRecord, String> cardNumCol;
    @FXML
    private TableColumn<cardRecord, String> cardExpiryCol;
    @FXML
    private TableColumn<cardRecord, String> cardSecCol;
    @FXML
    private TableColumn<cardRecord, Integer> cardEmployeeIDCol;
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
    private TableColumn<bankAccount, Integer> bankEmployeeIDCol;
    @FXML
    private ContextMenu bankContext;
    @FXML
    private ContextMenu cardContext;
    @FXML
    private AnchorPane contentPane;


    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(TreasuryDashboardController.class.getName());
    }

    /**
     * Handles the click event for the Add Credit Card Button
     * Opens addCreditCardPopup.fxml in a new window, without closing the old one
     * @param event The captured event of clicking the button
     */
    @FXML
    protected void addCreditCardClicked(ActionEvent event){
        Stage creditCardWindow = new Stage();
        loadIcon(creditCardWindow);
        creditCardWindow.setTitle("Add New Credit Card");
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addCreditCardPopup.fxml"));
        try{
            creditCardWindow.setScene(new Scene(loader.load()));
        }
        catch(IOException e){
            log.info("Add New Credit Card window was unable to be loaded in addCreditCardClicked.");
        }
        creditCardWindow.show();
        log.info("Add New Credit Card window was loaded successfully in addCreditCardClicked.");
    }


    /**
     * Handles the click event for the Add Bank Account Button
     * Opens addBankPopup.fxml in a new window, without closing the old one
     * @param event The captured event of clicking the button
     */
    @FXML
    protected void addBankClicked(ActionEvent event){
        Stage bankWindow = new Stage();
        loadIcon(bankWindow);
        bankWindow.setTitle("Add New Bank Account");
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addBankPopup.fxml"));
        try{
            bankWindow.setScene(new Scene(loader.load()));
        }
        catch(IOException e){
            log.info("Add New Bank Account window was unable to be loaded in addBankClicked.");
        }
        bankWindow.show();
        log.info("Add New Bank Account window was loaded successfully in addBankClicked.");
    }

    /**
     * This method handles a click event for opening the accordian view of the Card Table
     * When pressed, the tableView is refreshed with a new pull of data from the database
     */
    @FXML
    protected void cardMousePressed(){
        refreshCardTable();
        log.info("Card Table successfully updated from Mouse in cardMousePressed.");
    }

    /**
     * This method handles a click event for opening the accordian view of the Bank Table
     * When pressed, the tableView is refreshed with a new pull of data from the database
     */
    @FXML
    protected void bankMousePressed(){
        refreshBankTable();
        log.info("Bank Table successfully updated from Mouse in bankMousePressed.");
    }

    /**
     * This method handles clicks on individual rows in the bankTable
     * Two left clicks open the associated transactions for that bank account
     * This is done via opening a new window with bankAccountTransactions.fxml that displays a table of transactions
     * One right click opens the context menu for that row (bankContext)
     * @param event the captured click event
     */
    @FXML
    protected void itemClickedBank(MouseEvent event){
        if(event.getButton()==MouseButton.PRIMARY)
        {
            if((event).getClickCount() == 2)
            {
                selectedAccount = bankTable.getSelectionModel().getSelectedItem();
                Stage creditCardWindow = new Stage();
                loadIcon(creditCardWindow);
                creditCardWindow.setTitle("Bank Account Transaction List");
                FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("bankAccountTransactions.fxml"));
                try{
                    creditCardWindow.setScene(new Scene(loader.load()));
                }
                catch(IOException e){
                    log.info("Bank Account Transaction window was unable to be loaded in itemClickedBank.");
                }
                creditCardWindow.show();
                log.info("Bank Account Transaction window was loaded successfully in itemClickedBank.");
            }
        }
        else if(event.getButton()==MouseButton.SECONDARY){
            bankContext.show(bankTable, event.getScreenX(),event.getScreenY());
        }
    }


    /**
     * This method handles clicks on individual rows in the cardTable
     * Two left clicks open the associated transactions for that credit card
     * This is done via opening a new window with cardTransactions.fxml that displays a table of transactions
     * One right click opens the context menu for that row (cardContext)
     * @param event the captured click event
     */
    @FXML
    protected void itemClickedCard(MouseEvent event){
        if(event.getButton()== MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                selectedCard = cardTable.getSelectionModel().getSelectedItem();
                Stage creditCardWindow = new Stage();
                loadIcon(creditCardWindow);
                creditCardWindow.setTitle("Credit Card Transaction List");
                FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("cardTransactions.fxml"));
                try{
                    creditCardWindow.setScene(new Scene(loader.load()));
                }
                catch(IOException e){
                    log.info("Credit Card Transaction window was unable to be loaded in itemClickedCard.");
                }
                creditCardWindow.show();
                log.info("Credit Card Transaction window was loaded successfully in itemClickedCard.");
            }
        }
        else if(event.getButton()== MouseButton.SECONDARY){
            cardContext.show(cardTable, event.getScreenX(),event.getScreenY());
            log.info("Card Record ContextMenu shown.");
        }
    }

    /**
     * queries dbo.andrewCardRecord for a ResultSet of all Card Records in the table
     * iterates through the result in order to place them in an Observable List
     * @param connection the database connection from ParentController
     * @return an ObservableList filled with all Card Records in the table
     */
    protected ObservableList<cardRecord> getRecords(Connection connection){
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dbo.andrewCardRecord");
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            log.info("Select query from dbo.andrewCardRecord in getRecords failed.");
        }
        log.info("Select query from dbo.andrewCardRecord in getRecords succeeded.");

        ObservableList<cardRecord> oL = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                cardRecord temp = new cardRecord(rs.getString("CardName"),
                        rs.getString("CardNum"),
                        rs.getString("CardExpiry"),
                        rs.getString("CardSec"),
                        rs.getInt("EmployeeID"));
                oL.add(temp);
            }
        }
        catch(NullPointerException e){
            log.info("ResultSet rs was never filled in getRecords.");
        }
        catch(SQLException e){
            log.info("A column label was not valid for rs in getRecords.");
        }
        log.info("ObservableList successfully created in getRecords.");

        return oL;
    }

    /**
     * queries dbo.andrewBankAccounts for a ResultSet of all Bank Accounts in the table
     * iterates through the result in order to place them in an Observable List
     * @param connection the database connection from ParentController
     * @return an ObservableList filled with all Bank Accounts in the table
     */
    protected ObservableList<bankAccount> getAccounts(Connection connection){
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dbo.andrewBankAccounts");
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            log.info("Select query from dbo.andrewBankAccounts in getAccounts failed.");
        }
        log.info("Select query from dbo.andrewBankAccounts in getAccounts succeeded.");

        ObservableList<bankAccount> oL = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                bankAccount temp = new bankAccount(rs.getString("bankName"),
                        rs.getString("accountNum"),
                        rs.getString("routeNum"),
                        rs.getInt("EmployeeID"));
                oL.add(temp);
            }
        }
        catch(NullPointerException e){
            log.info("ResultSet rs was never filled in getAccounts.");
        }
        catch(SQLException e){
            log.info("A column label was not valid for rs in getAccounts.");
        }
        log.info("ObservableList successfully created in getAccounts.");

        return oL;
    }

    /**
     * a callable method to refresh the Bank tableView from a call of getAccounts(con)
     * fills each column with CellValueFactories
     */
    protected void refreshBankTable(){
            ObservableList<bankAccount> accounts = getAccounts(con);

            bankNameCol.setCellValueFactory(new PropertyValueFactory<>("bankName"));
            accountNumCol.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
            routingNumCol.setCellValueFactory(new PropertyValueFactory<>("routingNum"));
            bankEmployeeIDCol.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));

            bankTable.setItems(accounts);

            log.info("Bank Account tableView columns filled.");
    }

    /**
     * a callable method to refresh the Card Record tableView from a call of getRecords(con)
     * fills each column with CellValueFactories
     */
    protected void refreshCardTable(){
            ObservableList<cardRecord> records = getRecords(con);

            cardNameCol.setCellValueFactory(new PropertyValueFactory<>("cardName"));
            cardNumCol.setCellValueFactory(new PropertyValueFactory<>("cardNum"));
            cardExpiryCol.setCellValueFactory(new PropertyValueFactory<>("cardExpiry"));
            cardSecCol.setCellValueFactory(new PropertyValueFactory<>("cardSec"));
            cardEmployeeIDCol.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));

            cardTable.setItems(records);

            log.info("Card Records tableView columns filled.");
    }

    /**
     * this method runs after all other methods/initializations, at the start of the window being created
     * initializes cardContext and bankContext ContextMenus
     * adds click events for MenuItems in each ContextMenu
     */
    @FXML
    public void initialize(){
        cardContext = new ContextMenu();
        MenuItem cardItem = new MenuItem("Delete");
        cardContext.getItems().add(cardItem);

        bankContext = new ContextMenu();
        MenuItem bankItem = new MenuItem("Delete");
        bankContext.getItems().add(bankItem);
        cardItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                PreparedStatement ps;
                selectedCard = cardTable.getSelectionModel().getSelectedItem();

                try{
                    ps = con.prepareStatement("Delete FROM dbo.andrewTransactions WHERE cardNum = ?");
                    ps.setString(1, selectedCard.getCardNum());
                    ps.execute();
                }
                catch(SQLException e){
                    cardContext.hide();
                    log.info("Entry deletion in dbo.andrewTransactions in initialize/cardItem failed.");
                }

                log.info("Entry deletion in dbo.andrewTransactions in initialize/cardItem succeeded.");

                try {
                    ps = con.prepareStatement("DELETE FROM dbo.andrewCardRecord WHERE cardNum = ?");
                    ps.setString(1, selectedCard.getCardNum());
                    ps.execute();
                }
                catch(SQLException e){
                    cardContext.hide();
                    log.info("Entry deletion in dbo.andrewCardRecord in initialize/cardItem failed.");
                }

                log.info("Entry deletion in dbo.andrewTransactions in initialize/cardItem succeeded.");
                cardContext.hide();
                refreshCardTable();
                log.info("All deletions performed successfully in initialize/cardItem.");
            }
        });
        bankItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                PreparedStatement ps;
                selectedAccount = bankTable.getSelectionModel().getSelectedItem();
                try {
                    ps = con.prepareStatement("DELETE FROM dbo.andrewTransactions WHERE accountNum = ?");
                    ps.setString(1, selectedAccount.getAccountNum());
                    ps.execute();
                }
                catch(SQLException e){
                    bankContext.hide();
                    log.info("Entry deletion in dbo.andrewTransactions in initialize/bankItem failed.");
                }

                log.info("Entry deletion in dbo.andrewTransactions in initialize/bankItem succeeded.");

                try {
                    ps = con.prepareStatement("DELETE FROM dbo.andrewBankAccounts WHERE accountNum = ?");
                    ps.setString(1, selectedAccount.getAccountNum());
                    ps.execute();
                }
                catch(SQLException e){
                    bankContext.hide();
                    log.info("Entry deletion in dbo.andrewBankAccounts in initialize/bankItem failed.");
                }

                log.info("Entry deletion in dbo.andrewBankAccounts in initialize/bankItem succeeded.");
                bankContext.hide();
                refreshBankTable();
                log.info("All deletions performed successfully in initialize/bankItem.");
            }
        });

        FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }
}



