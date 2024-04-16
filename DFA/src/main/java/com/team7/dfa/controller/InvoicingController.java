package com.team7.dfa.controller;


import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.model.Graph;
import com.team7.dfa.model.InvoiceLog;
import com.team7.dfa.model.InvoiceModel;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is the controller for the invoicing view.
 * It is responsible for handling the user input and responding to the user.
 */
public class InvoicingController extends ParentController {
    public Button invoiceAddButton;
    @FXML
    private Button invoiceButton;

    /*
    * 0 - Receivable Invoice
    * 1 - Receivable Retainer Invoice
    * 2 - Receivable Recurring Invoice
    * 3 - Payable Invoice
    * 4 - Payable Retainer Invoice
    * 5 - Payable Recurring Invoice
    */
    public static int invoiceState = 0;

    @FXML
    public TableView<InvoiceModel> invoiceTable;
    @FXML
    public TableColumn<InvoiceModel, String> invoiceDateCol;
    @FXML
    public TableColumn<InvoiceModel, String> invoiceIDCol;
    @FXML
    public TableColumn<InvoiceModel, String> invoiceCustCol;
    @FXML
    public TableColumn<InvoiceModel, String> invoiceGroupCol;
    @FXML
    public TableColumn<InvoiceModel, String> invoiceStatusCol;
    @FXML
    public TableColumn<InvoiceModel, Integer> invoiceAmountCol;
    @FXML
    private BorderPane invoicingDashPane;
    @FXML
    private BorderPane invoicingTablePane;
    @FXML
    private Label invoicesTableTitle;
    @FXML
    private Button recInvButton;
    @FXML
    private Button recRetButton;
    @FXML
    private Button recRecButton;
    @FXML
    private Button payInvButton;
    @FXML
    private Button payRecButton;
    @FXML
    private Button payRetButton;
    @FXML
    public TableView<InvoiceLog> invoicingUpdateTable;
    @FXML
    public TableColumn<InvoiceLog, String> log_id_col;
    @FXML
    public TableColumn<InvoiceLog, String> log_update_col;
    @FXML
    public TableColumn<InvoiceLog, String> log_desc_col;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private ImageView invoiceImage;
    @FXML
    private AnchorPane invoicingLineChart;


    @FXML
    public void initialize() {
        generateGraph();
        invoicingDashPane.setVisible(true);
        invoicingTablePane.setVisible(false);
        refreshLogTable();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    /**
     * This method is called within initialize to generate the invoicing graph.
     */
    protected void generateGraph()
    {
        String sqlQuery = "SELECT Month, SUM(total_payable) OVER (ORDER BY month) AS cumulative_payable, SUM(total_receivable) OVER (ORDER BY month) AS cumulative_receivable FROM (SELECT CAST(FORMAT(CONVERT(date, inv_date, 120), 'yyyy-MM') AS varchar(7)) AS month, SUM(CASE WHEN inv_id LIKE 'P[S,D,R]%' THEN inv_total ELSE 0 END) AS total_payable, SUM(CASE WHEN inv_id LIKE '[S,D,R]%' THEN inv_total ELSE 0 END) AS total_receivable FROM dannyInvoiceRecords WHERE inv_id LIKE 'P[S,D,R]%' OR inv_id LIKE '[S,D,R]%' GROUP BY CAST(FORMAT(CONVERT(date, inv_date, 120), 'yyyy-MM') AS varchar(7))) AS monthly_totals ORDER BY month;";
        Graph profitdoubleline = new Graph(sqlQuery,
                "5",
                "Month",
                "cumulative_payable",
                "cumulative_receivable",
                "Payables and Receivables");

        invoiceImage.setPreserveRatio(true);
        invoiceImage.setManaged(false);
        invoiceImage.fitHeightProperty().bind(invoicingLineChart.heightProperty());
        invoiceImage.fitWidthProperty().bind(invoicingLineChart.widthProperty());
        profitdoubleline.updateGraphImage(invoiceImage);
    }

    /**
     * This method is called when the user switches to a regular invoice on the invoicing view.
     * It calls the refreshInvoiceTable method to populate the table with the regular invoices using the button click event.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void invButtonClick(ActionEvent event)
    {
        if(invoiceState > 2)
            payInvButtonClick(event);
        else
            recInvButtonClick(event);
    }

    /**
     * This method is called when the user switches to a retainer invoice on the invoicing view.
     * It calls the refreshInvoiceTable method to populate the table with the retainer invoices using the button click event.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void retButtonClick(ActionEvent event)
    {
        if(invoiceState > 2)
            payRetButtonClick(event);
        else
            recRetButtonClick(event);
    }

    /**
     * This method is called when the user switches to a recurring invoice on the invoicing view.
     * It calls the refreshInvoiceTable method to populate the table with the recurring invoices using the button click event.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void recButtonClick(ActionEvent event)
    {
        if(invoiceState > 2)
            payRecButtonClick(event);
        else
            recRecButtonClick(event);
    }

    /**
     * This method is called when the user clicks on the Receivable Invoice button.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void recInvButtonClick(ActionEvent event)
    {
        invoiceState = 0;
        invoicesTableTitle.setText("Receivable Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    /**
     * This method is called when the user clicks on the Receivable Retainer Invoice button.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void recRetButtonClick(ActionEvent event)
    {
        invoiceState = 1;
        invoicesTableTitle.setText("Receivable Retainer Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    /**
     * This method is called when the user clicks on the Receivable Recurring Invoice button.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void recRecButtonClick(ActionEvent event)
    {
        invoiceState = 2;
        invoicesTableTitle.setText("Receivable Recurring Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    /**
     * This method is called when the user clicks on the Payable Invoice button.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void payInvButtonClick(ActionEvent event)
    {
        invoiceState = 3;
        invoicesTableTitle.setText("Payable Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    /**
     * This method is called when the user clicks on the Payable Retainer Invoice button.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void payRetButtonClick(ActionEvent event)
    {
        invoiceState = 4;
        invoicesTableTitle.setText("Payable Retainer Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    /**
     * This method is called when the user clicks on the Payable Recurring Invoice button.
     * It loads the invoicing view.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void payRecButtonClick(ActionEvent event)
    {
        invoiceState = 5;
        invoicesTableTitle.setText("Payable Recurring Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    /**
     * This method is called when invoices need to be refreshed, either when a view is loaded or done manually.
     * It queries the database for the invoices and populates the table with the results.
     */
    protected void refreshInvoiceTable() {
        try {
            String queryString = "select * from [dbo].[dannyInvoiceRecords] where inv_id like " + switch (invoiceState) {
                case 0 -> "'S%'";
                case 1 -> "'D%'";
                case 2 -> "'R%'";
                case 3 -> "'PS%'";
                case 4 -> "'PD%'";
                case 5 -> "'PR%'";
                default -> "'%'";
            };

            ObservableList<InvoiceModel> invoiceList = FXCollections.observableArrayList();
            PreparedStatement ps = con.prepareStatement(queryString);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                invoiceList.add(new InvoiceModel(rs.getString("inv_date"),
                        rs.getString("inv_id"),
                        rs.getString("inv_cust_name"),
                        rs.getString("inv_group"),
                        rs.getString("inv_state"),
                        rs.getInt("inv_total")));

            invoiceDateCol.setCellValueFactory(new PropertyValueFactory<>("inv_date"));
            invoiceIDCol.setCellValueFactory(new PropertyValueFactory<>("inv_id"));
            invoiceCustCol.setCellValueFactory(new PropertyValueFactory<>("inv_cust_name"));
            invoiceGroupCol.setCellValueFactory(new PropertyValueFactory<>("inv_group"));
            invoiceStatusCol.setCellValueFactory(new PropertyValueFactory<>("inv_state"));
            invoiceAmountCol.setCellValueFactory(new PropertyValueFactory<>("inv_total"));

            invoiceTable.setItems(invoiceList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called when the log table needs to be refreshed, either when a view is loaded or done manually.
     * It queries the database for the log entries and populates the table with the results.
     */
    protected void refreshLogTable() {
        try {
            ObservableList<InvoiceLog> logList = FXCollections.observableArrayList();
            PreparedStatement ps = con.prepareStatement("select * from [dbo].[invoiceLog]");
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                logList.add(new InvoiceLog(rs.getString("log_updated"),
                        rs.getString("log_inv_id"),
                        rs.getString("log_desc")));

            log_update_col.setCellValueFactory(new PropertyValueFactory<>("log_date"));
            log_id_col.setCellValueFactory(new PropertyValueFactory<>("log_id"));
            log_desc_col.setCellValueFactory(new PropertyValueFactory<>("log_desc"));

            invoicingUpdateTable.setItems(logList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the user clicks the add invoice button.
     * It loads the add edit invoice view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void invoiceAddClicked(MouseEvent event) throws IOException {
        InvoiceViewController.grabbed = null;
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addEditInvoice.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Invoice");
        loadIcon(stage);
        stage.show();
    }

    /**
     * This method is called when the user double-clicks an invoice row on the invoice table.
     * It loads the add edit invoice view.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void handleRowSelect(Event event) throws IOException {
        if(event.getEventType().getName().equals("MOUSE_CLICKED"))
        {
            if(((javafx.scene.input.MouseEvent) event).getClickCount() == 2)
            {
                if(invoiceTable.getSelectionModel().getSelectedItem() == null)
                    return;
                InvoiceModel selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();
                grabInvoice(selectedInvoice.getInv_id());
                FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addEditInvoice.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Invoice");
                loadIcon(stage);
                stage.show();
            }
        }
    }

    /**
     * This method is called when the user clicks the refresh button.
     * It refreshes the invoice table.
     * @param event The event that triggered the method call
     */
    @FXML
    protected void refresh_clicked(ActionEvent event) {
        refreshInvoiceTable();
    }

    protected void grabInvoice(String inv_id) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from [dbo].[dannyInvoiceRecords] where inv_id = ?");
            ps.setString(1, inv_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            InvoiceModel invoice = new InvoiceModel(rs.getString("inv_date"),
                    rs.getString("inv_due_date"),
                    rs.getString("inv_id"),
                    rs.getString("inv_order_id"),
                    rs.getString("inv_cust_name"),
                    rs.getString("inv_shipping"),
                    rs.getString("inv_billing"),
                    rs.getString("inv_group"),
                    rs.getString("inv_state"),
                    rs.getDouble("inv_subtotal"),
                    rs.getDouble("inv_discount"),
                    rs.getDouble("inv_tax_rate"),
                    rs.getDouble("inv_total"),
                    rs.getDouble("inv_balance"),
                    rs.getInt("inv_repeat"),
                    rs.getString("inv_cust_notes"),
                    rs.getString("inv_terms"));
            InvoiceViewController.grabbed = invoice;
            log.info("Loaded invoice:\n" + invoice.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
