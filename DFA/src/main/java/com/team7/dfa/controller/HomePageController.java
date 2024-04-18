package com.team7.dfa.controller;

import com.team7.dfa.db.DatabaseConnector;
import com.team7.dfa.model.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.animation.FadeTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.util.Locale;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The HomePageController class controls the functionality of the home page of the DFA application.
 */
public class HomePageController extends ParentController {

    DatabaseConnector db = new DatabaseConnector();
    Connection conn = db.connect();

    @FXML
    private AnchorPane contentPane;

    static Logger log = null;

    @FXML
    private ImageView invoiceFlowGraphImage;

    @FXML
    private ImageView expenseGraphImage;

    @FXML
    private ImageView transactionGraphImage;

    @FXML
    private ListView<String> listView;


    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DatabaseConnector.class.getName());
    }

    public void initialize() {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    /**
     * Handles the action event when the generateGraphsButton is clicked.
     * Currently, it updates the graph1 ImageView with the correct graph image.
     */
    @FXML
    private void generateGraphButton(ActionEvent event) {
            log.info("Generating Graphs");
            Graph invoiceGraph = new Graph("select inv_state from dannyInvoiceRecords",
                    "inv_state",
                    "Invoice State Pie Chart");
            invoiceGraph.updateGraphImage(invoiceFlowGraphImage);

            Graph expenseGraph = new Graph("SELECT 'Employees Net Pay' AS \"category\", SUM(rp.Salary) AS \"total\" " +
                    "FROM rohanPayrollGraphData rp " +
                    "JOIN dannyInvoiceRecords di ON di.inv_ID LIKE '%P%' " +
                    "WHERE di.inv_total > 0 " +
                    "UNION ALL " +
                    "SELECT 'Payable Invoices' AS \"category\", SUM(di.inv_total) AS \"total\" " +
                    "FROM dannyInvoiceRecords di " +
                    "WHERE di.inv_ID LIKE '%P%' AND di.inv_total > 0;",
                    "2",
                    "category",
                    "total",
                    "Total Expenses"
                    );
            expenseGraph.updateGraphImage(expenseGraphImage);

            Graph transactionGraph = new Graph("SELECT FORMAT(CONVERT(date, date), 'MMMM d') AS \"date\", SUM(amount) AS \"amount\" FROM andrewTransactions GROUP BY date;",
                    "3",
                    "date",
                    "amount",
                    "Total Transactions");
            transactionGraph.updateGraphImage(transactionGraphImage);

            refreshActivity();

    }

    private void refreshActivity() {
        log.info("Refreshing Activity");
        refreshListView();
    }

    private void refreshListView () {
        try {
            String sqlQuery = "SELECT CONCAT(t.name, ' : ', SUM(p.rows), ' items') AS TableInfo " +
                    "FROM sys.tables t " +
                    "INNER JOIN sys.partitions p ON t.object_id = p.object_id " +
                    "WHERE t.is_ms_shipped = 0 AND p.index_id IN (0, 1) " +
                    "GROUP BY t.name " +
                    "ORDER BY SUM(p.rows) DESC";

            PreparedStatement statement = conn.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<String> tableInfoList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                tableInfoList.add(resultSet.getString("TableInfo"));
            }
            listView.setItems(tableInfoList);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throwError("Error refreshing activity. Please try again later.\nSee below error message to troubleshoot:\n" + e.getMessage());
        }
    }
}