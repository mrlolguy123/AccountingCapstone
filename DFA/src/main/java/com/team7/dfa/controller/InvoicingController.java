package com.team7.dfa.controller;

import com.team7.dfa.TemplateTestApplication;
import com.team7.dfa.db.DatabaseConnector;
import com.team7.dfa.model.InvoiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    int invoiceState = 0;

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
    protected void invButtonClick(ActionEvent event)
    {
        if(invoiceState > 2)
            payInvButtonClick(event);
        else
            recInvButtonClick(event);
    }
    @FXML
    protected void retButtonClick(ActionEvent event)
    {
        if(invoiceState > 2)
            payRetButtonClick(event);
        else
            recRetButtonClick(event);
    }
    @FXML
    protected void recButtonClick(ActionEvent event)
    {
        if(invoiceState > 2)
            payRecButtonClick(event);
        else
            recRecButtonClick(event);
    }
    @FXML
    protected void recInvButtonClick(ActionEvent event)
    {
        invoiceState = 0;
        invoicesTableTitle.setText("Receivable Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }
    @FXML
    protected void recRetButtonClick(ActionEvent event)
    {
        invoiceState = 1;
        invoicesTableTitle.setText("Receivable Retainer Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }
    @FXML
    protected void recRecButtonClick(ActionEvent event)
    {
        invoiceState = 2;
        invoicesTableTitle.setText("Receivable Recurring Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }
    @FXML
    protected void payInvButtonClick(ActionEvent event)
    {
        invoiceState = 3;
        invoicesTableTitle.setText("Payable Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }
    @FXML
    protected void payRetButtonClick(ActionEvent event)
    {
        invoiceState = 4;
        invoicesTableTitle.setText("Payable Retainer Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }
    @FXML
    protected void payRecButtonClick(ActionEvent event)
    {
        invoiceState = 5;
        invoicesTableTitle.setText("Payable Recurring Invoices");
        invoicingDashPane.setVisible(false);
        invoicingTablePane.setVisible(true);
        refreshInvoiceTable();
    }

    @FXML
    protected void invoiceButtonClick(ActionEvent event)
    {
        invoicingDashPane.setVisible(true);
        invoicingTablePane.setVisible(false);
    }

    protected void refreshInvoiceTable()
    {
        try
        {
            String queryString = "select * from [dbo].[dannyInvoiceRecords] where inv_id like " + switch (invoiceState)
            {
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

        } catch (SQLException e)
            {
                e.printStackTrace();
            }

    }

    @FXML
    protected void handleRowSelect(Event event)
    {
        if(event.getEventType().getName().equals("MOUSE_CLICKED"))
        {
            if(((javafx.scene.input.MouseEvent) event).getClickCount() == 2)
            {
                InvoiceModel selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();
                System.out.println(selectedInvoice.getInv_id()); // replace with new window
            }
        }
    }

    @FXML
    public void invoiceAddCLicked(MouseEvent mouseEvent) throws IOException {
        // load new window
        FXMLLoader loader = new FXMLLoader(TemplateTestApplication.class.getResource("addEditInvoice.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Invoice");
        stage.show();
    }
}
