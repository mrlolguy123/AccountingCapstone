package com.team7.dfa.controller;

import com.team7.dfa.model.InvoiceItemModel;
import com.team7.dfa.model.InvoiceModel;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is the controller for the add edit invoice view.
 * It contains the methods that are used to interact with the add edit invoice view.
 */
public class InvoiceViewController extends ParentController {
    @FXML
    private TableView<InvoiceItemModel> inv_item_table;
    @FXML
    private TableColumn<InvoiceItemModel, String> item_desc_col;
    @FXML
    private TableColumn<InvoiceItemModel, Integer> item_num_col;
    @FXML
    private TableColumn<InvoiceItemModel, Double> item_price_col;
    @FXML
    private TableColumn<InvoiceItemModel, Double> item_total_col;
    @FXML
    private TextField recur_field;
    @FXML
    private DatePicker date_field;
    @FXML
    private TextField id_field;
    @FXML
    private TextField customer_field;
    @FXML
    private Button item_add_button;
    @FXML
    private Button publish_button;
    @FXML
    private Button delete_button;
    @FXML
    private TextField order_field;
    @FXML
    private TextField subtotal_field;
    @FXML
    private TextField discount_field;
    @FXML
    private TextField balance_field;
    @FXML
    private TextField total_tax_field;
    @FXML
    private TextField total_field;
    @FXML
    private TextArea notes_field;
    @FXML
    private TextField term_field;
    @FXML
    private TextArea shipping_field;
    @FXML
    private TextArea billing_field;
    @FXML
    private TextField group_field;
    @FXML
    private ComboBox<String> state_field;
    @FXML
    private AnchorPane contentPane;

    int invoice_state = InvoicingController.invoiceState;

    public static InvoiceModel grabbed;

    /**
     * This method is called when the window opens.
     * If an invoice was clicked on the table, it fills in its info into the form.
     * If not, it leaves the form blank.
     */
    @FXML
    public void initialize() {
        // if the invoice is not null, fill out the fields with the invoice details
        if(grabbed != null)
        {
            id_field.setText(grabbed.getInv_id());
            customer_field.setText(grabbed.getInv_cust_name());
            date_field.setValue(LocalDate.parse(grabbed.getInv_date()));
            state_field.setValue(grabbed.getInv_state());
            recur_field.setText(String.valueOf(grabbed.getInv_repeat()));
            order_field.setText(grabbed.getInv_order_id());
            notes_field.setText(grabbed.getInv_cust_notes());
            term_field.setText(String.valueOf(grabbed.getInv_terms()));
            subtotal_field.setText(String.valueOf(grabbed.getInv_subtotal()));
            discount_field.setText(String.valueOf(grabbed.getInv_discount()));
            total_tax_field.setText(String.valueOf(grabbed.getInv_tax_rate()));
            state_field.setValue(grabbed.getInv_state());
            total_field.setText(String.valueOf(grabbed.getInv_total()));
            balance_field.setText(String.valueOf(grabbed.getInv_balance()));
            shipping_field.setText(grabbed.getInv_shipping());
            billing_field.setText(grabbed.getInv_billing());
            group_field.setText(grabbed.getInv_group());
        }
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    /**
     * This method is called when the user clicks the publish button on the add edit invoice view.
     * It adds or updates a row using saveNewInvoice or updateInvoice.
     * @param event The event that triggered the method call
     * @throws SQLException If the SQL query is invalid
     */
    @FXML
    protected void publish_clicked(ActionEvent event) throws SQLException, IOException {
        if(grabbed == null)
            saveNewInvoice(event);
        else
            updateInvoice(event);
    }

    /**
     * This method is called when the user clicks the publish button on the add edit invoice view.
     * It adds a new row to the invoice item table with the specified details filled out on the form.
     * @param event The event that triggered the method call
     * @throws SQLException If the SQL query is invalid
     */
    private void saveNewInvoice(ActionEvent event) throws SQLException {
        String id = findNextInvID();
        String customer = customer_field.getText();
        String date = date_field.getValue() != null ? date_field.getValue().toString() : "";
        String state = state_field.getValue();
        String recur = recur_field.getText();
        String order = order_field.getText();
        String cust_notes = notes_field.getText();
        String shipping = shipping_field.getText();
        String billing = billing_field.getText();
        String group = group_field.getText();

        if (customer.isEmpty() || date.isEmpty() || state == null || shipping.isEmpty() || billing.isEmpty() || term_field.getText().isEmpty() || subtotal_field.getText().isEmpty() || discount_field.getText().isEmpty() || total_tax_field.getText().isEmpty() || total_field.getText().isEmpty() || balance_field.getText().isEmpty())
        {
            throwError("All BOLDED fields must be filled out.");
        }

        // Validate numeric fields
        try {
            int term = Integer.parseInt(term_field.getText());
            Double subtotal = Double.parseDouble(subtotal_field.getText());
            Double discount = Double.parseDouble(discount_field.getText());
            Double total_tax = Double.parseDouble(total_tax_field.getText());
            Double total = Double.parseDouble(total_field.getText());
            Double balance = Double.parseDouble(balance_field.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date_formatted = LocalDate.parse(date, formatter);
            LocalDate due_date_formatted = date_formatted.plusDays(term);
            String due_date = due_date_formatted.format(formatter);

            String queryString = "insert into dannyInvoiceRecords values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(queryString);
            ps.setString(1, date);
            ps.setString(2, due_date);
            ps.setString(3, id);
            ps.setString(4, order);
            ps.setString(5, customer);
            ps.setString(6, shipping);
            ps.setString(7, billing);
            ps.setString(8, group);
            ps.setString(9, state);
            ps.setDouble(10, subtotal);
            ps.setDouble(11, discount);
            ps.setDouble(12, total_tax);
            ps.setDouble(13, total);
            ps.setDouble(14, balance);
            ps.setInt(15, term);

            int recur_result = 0;
            if(id.contains("R") && !recur.isEmpty()) {
                if (recur.matches("[0-9]+")) {
                    recur_result = Integer.parseInt(recur);
                }
                else {
                    throwError("Recurring invoices must have a valid recurring number.");
                    return;
                }
            }
            if(id.contains("R") && recur.isEmpty()) {
                throwError("Recurring invoices must have a valid recurring number.");
                return;
            }

            ps.setInt(16, recur_result);
            ps.setString(17, cust_notes);

            ps.executeUpdate();

            Stage stage = (Stage) date_field.getScene().getWindow();
            stage.close();
            grabbed = null;
            log.info("Invoice saved");
        } catch (NumberFormatException e) {
            throwError("Numeric fields must contain valid numbers.");
        } catch (DateTimeParseException e) {
            throwError("Invalid date format.");
        } catch (SQLException e) {
            throwError("There was an issue with your connection. Please try again later.");
        }
    }

    /**
     * This method is called when the user clicks the publish button on the add edit invoice view.
     * It updates a row to the invoice item table with the specified details filled out on the form.
     * @param event The event that triggered the method call
     * @throws SQLException If the SQL query is invalid
     */
    private void updateInvoice(ActionEvent event) throws SQLException {
        String id = grabbed.getInv_id();
        String customer = customer_field.getText();
        String date = date_field.getValue() != null ? date_field.getValue().toString() : "";
        String state = state_field.getValue();
        String recur = recur_field.getText();
        String order = order_field.getText();
        String cust_notes = notes_field.getText();
        String shipping = shipping_field.getText();
        String billing = billing_field.getText();
        String group = group_field.getText();

        if (customer.isEmpty() || date.isEmpty() || state == null || shipping.isEmpty() || billing.isEmpty() || term_field.getText().isEmpty() || subtotal_field.getText().isEmpty() || discount_field.getText().isEmpty() || total_tax_field.getText().isEmpty() || total_field.getText().isEmpty() || balance_field.getText().isEmpty()) {
            throwError("All BOLDED fields must be filled out.");
            return;
        }

        // Validate numeric fields
        try {
            int term = Integer.parseInt(term_field.getText());
            Double subtotal = Double.parseDouble(subtotal_field.getText());
            Double discount = Double.parseDouble(discount_field.getText());
            Double total_tax = Double.parseDouble(total_tax_field.getText());
            Double total = Double.parseDouble(total_field.getText());
            Double balance = Double.parseDouble(balance_field.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date_formatted = LocalDate.parse(date, formatter);
            LocalDate due_date_formatted = date_formatted.plusDays(term);
            String due_date = due_date_formatted.format(formatter);

            String queryString = "UPDATE dannyInvoiceRecords SET inv_date = ?, inv_due_date = ?, inv_cust_name = ?, inv_order_id = ?, inv_shipping = ?, inv_billing = ?, inv_group = ?, inv_state = ?, inv_subtotal = ?, inv_discount = ?, inv_tax_rate = ?, inv_total = ?, inv_balance = ?, inv_terms = ?, inv_repeat = ?, inv_cust_notes = ? WHERE inv_id = ?";
            PreparedStatement ps = con.prepareStatement(queryString);
            ps.setString(1, date);
            ps.setString(2, due_date);
            ps.setString(3, customer);
            ps.setString(4, order);
            ps.setString(5, shipping);
            ps.setString(6, billing);
            ps.setString(7, group);
            ps.setString(8, state);
            ps.setDouble(9, subtotal);
            ps.setDouble(10, discount);
            ps.setDouble(11, total_tax);
            ps.setDouble(12, total);
            ps.setDouble(13, balance);
            ps.setInt(14, term);

            int recur_result = 0;
            if (id.contains("R")) {
                if (recur.isEmpty() || !recur.matches("[0-9]+")) {
                    throwError("Recurring invoices must have a valid recurring number.");
                    return;
                }
                recur_result = Integer.parseInt(recur);
            }
            ps.setInt(15, recur_result);
            ps.setString(16, cust_notes);
            ps.setString(17, id);

            ps.executeUpdate();

            Stage stage = (Stage) date_field.getScene().getWindow();
            stage.close();
            log.info("Invoice updated");
        } catch (NumberFormatException e) {
            throwError("Numeric fields must contain valid numbers.");
        } catch (DateTimeParseException e) {
            throwError("Invalid date format.");
        } catch (SQLException e) {
            throwError("There was an issue with your connection. Please try again later.");
        }
    }

    /**
     * This method is called when the user within the publish_clicked function.
     * It finds a new invoice ID based on the current invoice state.
     * @return The new invoice ID
     * @throws SQLException If the SQL query is invalid
     */
    private String findNextInvID() throws SQLException {
        int adjust = switch (invoice_state) {
            case 0:
            case 1:
            case 2:
                yield 0;
            case 3:
            case 4:
            case 5:
                yield 1;
            default:
                throw new IllegalStateException("Unexpected value: " + invoice_state);
        };

        String type = switch (invoice_state) {
            case 0 -> "S";
            case 1 -> "D";
            case 2 -> "R";
            case 3 -> "PS";
            case 4 -> "PD";
            case 5 -> "PR";
            default -> "";
        };

        try {
            String queryString = "select top 1 inv_id from dannyInvoiceRecords where inv_id like ? + '%' order by cast(substring(inv_id, ?, len(inv_id) - ?) as int) desc";
            PreparedStatement ps = con.prepareStatement(queryString);
            ps.setString(1, type);
            ps.setInt(2, adjust + 2);
            ps.setInt(3, adjust + 1);

            ResultSet rs = ps.executeQuery();
            rs.next();

            int id = Integer.parseInt(rs.getString("inv_id").substring(adjust + 1)) + 1;
            String lz = String.format("%04d", id);
            return type + lz;
        } catch (SQLException e) {
            throwError("There was an issue with your connection. Please try again later.");
            return "";
        }
    }

    /**
     * This method is called when the user clicks the delete button on the add edit invoice view.
     * It either functions as the close button (if the invoice is not saved) or as the delete invoice button to delete a saved row from the database.
     * @param event The event that triggered the method call
     * @throws IOException If the FXML file is not found
     */
    @FXML
    protected void delete_clicked(ActionEvent event) throws SQLException, IOException {
        try {
            if (grabbed != null) {
                String id = grabbed.getInv_id();
                String queryString = "delete from dannyInvoiceRecords where inv_id = ?";
                PreparedStatement ps = con.prepareStatement(queryString);
                ps.setString(1, id);
                ps.executeUpdate();
            }
            Stage stage = (Stage) date_field.getScene().getWindow();
            stage.close();
            grabbed = null;
            log.info("Invoice deleted");
        } catch (SQLException e) {
            throwError("There was an issue with your connection. Please try again later.");
        }
    }
}
