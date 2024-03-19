package com.team7.dfa.controller;

import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.model.cardRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

import static com.team7.dfa.TemplateTestApplication.connect;

public class TreasuryDashboardController {

    @FXML
    public TableColumn cardNameCol;
    @FXML
    public TableColumn cardNumCol;
    @FXML
    public TableColumn cardExpiryCol;
    @FXML
    public TableColumn cardSecCol;
    @FXML
    private TitledPane cardPane;
    @FXML
    private TableView cardTable;
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

    @FXML
    protected void logoutClicked(ActionEvent event)
    {
        System.exit(0);
    }
    @FXML
    protected void addCreditCardClicked(ActionEvent event) throws IOException {
        Stage creditCardWindow = new Stage();
        creditCardWindow.setTitle("Add New Credit Card");
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addCreditCardPopup.fxml"));
        creditCardWindow.setScene(new Scene(loader.load()));
        creditCardWindow.show();
    }
    @FXML
    protected void addBankClicked(ActionEvent event) throws IOException{
        Stage bankWindow = new Stage();
        bankWindow.setTitle("Add New Bank Account");
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addBankPopup.fxml"));
        bankWindow.setScene(new Scene(loader.load()));
        bankWindow.show();
    }
    @FXML
    protected void importStatementClicked(ActionEvent event) throws IOException{
        //do nothing rn
    }

    @FXML
    protected void mousePressed() throws IOException, SQLException {

        ObservableList<cardRecord> records = getRecords(connect.getConnection());


        cardNameCol.setCellValueFactory(new PropertyValueFactory<cardRecord,String>("cardName"));
        cardNumCol.setCellValueFactory(new PropertyValueFactory<cardRecord,String>("cardNum"));
        cardExpiryCol.setCellValueFactory(new PropertyValueFactory<cardRecord,String>("cardExpiry"));
        cardSecCol.setCellValueFactory(new PropertyValueFactory<cardRecord,String>("cardSec"));

        cardTable.getColumns().setAll(cardNameCol,cardNumCol,cardExpiryCol,cardSecCol);

        cardTable.setItems(records);


    }

    protected ObservableList<cardRecord> getRecords(Connection connection) throws SQLException,IOException{
        ResultSet rs = readData(connection);
        ObservableList<cardRecord> oL = FXCollections.observableArrayList();
        while (rs.next()){
            cardRecord temp = new cardRecord(rs.getString("CardName"),
                    rs.getString("CardNum"),
                    rs.getString("CardExpiry"),
                    rs.getString("CardSec"));
            oL.addAll(temp);
        }
        return oL;
    }

    protected ResultSet readData(Connection connection) throws SQLException,IOException{
        Statement stmt = connection.createStatement();
        String SQL = "SELECT * FROM dbo.andrewCardRecord;";
        ResultSet rs = stmt.executeQuery(SQL);
        return rs;
    }

}
