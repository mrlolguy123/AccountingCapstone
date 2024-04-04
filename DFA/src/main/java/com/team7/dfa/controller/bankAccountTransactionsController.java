package com.team7.dfa.controller;

import com.team7.dfa.model.transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

import static com.team7.dfa.controller.TreasuryDashboardController.selectedAccount;

/**
 * Controls the methods and click events for bankAccountTransactions.fxml
 */
public class bankAccountTransactionsController extends ParentController{
    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(cardTransactionsController.class.getName());
    }

    private String account = selectedAccount.getAccountNum();
    private transactions selectedTransaction = null;
    @FXML
    private TableColumn<transactions, String> transIDCol;
    @FXML
    private TableColumn<transactions, String> accountNumCol;
    @FXML
    private TableColumn<transactions, Date> dateCol;
    @FXML
    private TableColumn<transactions, Integer> amountCol;
    @FXML
    private Button closeButton;
    @FXML
    private TableView<transactions> bankTransTable;
    @FXML
    private TextField amountText;
    @FXML
    private TextField dateText;
    @FXML
    private ContextMenu bankContext;

    /**
     * Closes the window for bankAccountTransactions.fxml
     */
    @FXML
    protected void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        log.info("bankAccountTransactions.fxml window closed.");
    }

    /**
     * a callable method to refresh the transaction tableView from a call of getTransactions(con)
     * fills each column using CellValueFactories
     */
    @FXML
    protected void refreshBankTable(){
        ObservableList<transactions> transactions = getTransactions(con);

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        accountNumCol.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
        transIDCol.setCellValueFactory(new PropertyValueFactory<>("transID"));

        bankTransTable.setItems(transactions);

        log.info("Bank Account tableView columns filled in refreshBankTable.");
    }

    /**
     * Handles click events for the rows in bankTransTable
     * left click fills a variable that acts as the currently selected row
     * right click opens a context menu for a row
     * @param event the captured click event
     */
    @FXML
    protected void itemClickedBank(MouseEvent event){
        if(event.getButton()==MouseButton.SECONDARY){
            bankContext.show(bankTransTable, event.getScreenX(),event.getScreenY());
            log.info("Bank Transaction ContextMenu shown.");
        }
        else if(event.getButton()==MouseButton.PRIMARY){
            selectedTransaction = bankTransTable.getSelectionModel().getSelectedItem();
            log.info("Bank Transaction selected.");
        }
    }

    /**
     * queries dbo.andrewTransactions for a ResultSet of all transactions in the table
     * iterates through the result in order to place them in an Observable List
     * @param connection the database connection from ParentController
     * @return an ObservableList filled with all transactions in the table
     */
    protected ObservableList<transactions> getTransactions(Connection connection){
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dbo.andrewTransactions WHERE accountNum = ?");
            ps.setString(1, account);
            rs = ps.executeQuery();
        }
        catch(SQLException e){
            log.info("Select query from dbo.andrewTransactions in getTransactions failed.");
        }
        log.info("Select query from dbo.andrewTransactions in getTransactions succeeded.");

        ObservableList<transactions> oL = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                transactions temp = new transactions(rs.getDate("date"),
                        rs.getInt("amount"),
                        rs.getString("accountNum"),
                        rs.getString("cardNum"),
                        rs.getInt("transID"));
                oL.add(temp);
            }
        }
        catch(NullPointerException e){
            log.info("ResultSet rs was never filled in getTransactions.");
        }
        catch(SQLException e){
            log.info("A column label was not valid for rs in getTransactions.");
        }
        log.info("ObservableList successfully created in getTransactions.");

        return oL;
    }

    /**
     * When add is clicked, creates a transaction from values in textFields
     */
    public void addTransaction(){
        refreshBankTable();

        try {
            PreparedStatement insertStatement = con.prepareStatement("INSERT INTO dbo.andrewTransactions VALUES (?,?,?,?);");
            insertStatement.setDate(1, java.sql.Date.valueOf(dateText.getText()));
            insertStatement.setInt(2, Integer.parseInt(amountText.getText()));
            insertStatement.setString(3, account);
            insertStatement.setString(4, null);
            insertStatement.executeUpdate();
        }
        catch(SQLException e){
            log.info("Insert into dbo.andrewTransactions in addTransaction failed.");
        }

        log.info("Insert into dbo.andrewTransactions in addTransaction succeeded.");

        refreshBankTable();
    }

    /**
     * this method runs after all other methods/initializations, at the start of the window being created
     * initializes bankContext ContextMenu
     * adds click event for MenuItem in bankContext
     */
    @FXML
    public void initialize(){
        refreshBankTable();
        bankContext = new ContextMenu();
        MenuItem bankItem = new MenuItem("Delete");
        bankContext.getItems().add(bankItem);
        bankItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                PreparedStatement ps;
                try {
                    ps = con.prepareStatement("DELETE FROM dbo.andrewTransactions WHERE accountNum = ?");
                    ps.setString(1, account);
                    ps.execute();
                }
                catch(SQLException e){
                    bankContext.hide();
                    log.info("Entry deletion in dbo.andrewTransactions in initialize/bankItem failed.");
                }

                log.info("Entry deletion in dbo.andrewTransactions in initialize/bankItem succeeded.");
                bankContext.hide();
                refreshBankTable();
                log.info("All deletions performed successfully in initialize/bankItem.");
            }
        });
    }

    /**
     * method to edit a selected transaction row, replaces entry's values with filled textFields
     * @param mouseEvent the captured mouse click
     */
    @FXML
    public void editTransaction(MouseEvent mouseEvent){
        if(selectedTransaction!=null){

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE dbo.andrewTransactions SET amount = ?, date = ? WHERE transID = ?");
                ps.setInt(1, Integer.parseInt(amountText.getText()));
                ps.setDate(2, java.sql.Date.valueOf(dateText.getText()));
                ps.setInt(3, selectedTransaction.getTransID());
                ps.execute();
            }
            catch(SQLException e){
                log.info("Update in dbo.andrewTransactions in editTransaction failed.");
            }

            log.info("Update in dbo.andrewTransactions in editTransaction succeeded.");

            refreshBankTable();
        }
    }
}
