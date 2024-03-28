package com.team7.dfa.controller;

import com.team7.dfa.controller.ParentController;
import com.team7.dfa.model.transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.Date;
import java.util.logging.Logger;

public class bankAccountTransactionsController extends ParentController {
    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(cardTransactionsController.class.getName());
    }

    String account;
    @FXML
    private Button refreshButton;
    @FXML
    private TableColumn<transactions, String> transIDCol;
    @FXML
    private TableColumn<transactions, String> accountNumCol;
    @FXML
    private TableColumn<transactions, Date> dateCol;
    @FXML
    private TableColumn<transactions, Integer> amountCol;
    @FXML
    private Button addButton;
    @FXML
    private Button closeButton;
    @FXML
    private TableView<transactions> bankTransTable;
    @FXML
    private TextField amountText;
    @FXML
    private TextField dateText;

    @FXML
    protected void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    // refreshes the table with current data
    @FXML
    protected void refreshTableBank() throws SQLException {
        account = TreasuryDashboardController.selectedAccount.getAccountNum();
        System.out.println(account);
        ResultSet rs = readTransactions(con);

        ObservableList<transactions> oL = FXCollections.observableArrayList();
        while (rs.next()){
            try {
                transactions temp = new transactions(rs.getDate("date"),
                        rs.getInt("amount"),
                        rs.getString("accountNum"),
                        rs.getString("cardNum"),
                        rs.getInt("transID"));
                oL.add(temp);
            }catch(Exception e){
                log.info("Error reading query: ");
                e.printStackTrace();
            }
        }

        try {
            dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
            accountNumCol.setCellValueFactory(new PropertyValueFactory<>("accountNum"));
            transIDCol.setCellValueFactory(new PropertyValueFactory<>("transID"));

            bankTransTable.setItems(oL);
        } catch(Exception e){
            log.info("Could not fill Transaction Table");
        }

    }

    protected ResultSet readTransactions(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM dbo.andrewTransactions WHERE accountNum = ?");
        ps.setString(1,account);
        return ps.executeQuery();

    }

    public void addTransaction() throws SQLException {
        refreshTableBank();
        log.info("Reading entry");
        PreparedStatement insertStatement = con.prepareStatement("INSERT INTO dbo.andrewTransactions VALUES (?,?,?,?);");
        insertStatement.setDate(1, java.sql.Date.valueOf(dateText.getText()));
        insertStatement.setInt(2, Integer.parseInt(amountText.getText()));
        insertStatement.setString(3,account);
        insertStatement.setString(4,null);
        insertStatement.executeUpdate();
        log.info("Updating database");
        refreshTableBank();
    }
}
