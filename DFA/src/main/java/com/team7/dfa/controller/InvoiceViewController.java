package com.team7.dfa.controller;

import com.team7.dfa.model.InvoiceItemModel;
import com.team7.dfa.model.InvoiceModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;

public class InvoiceViewController extends ParentController {
    /**
     * This class is the controller for the add edit invoice view.
     * It contains the methods that are used to interact with the add edit invoice view.
     */

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
    private ComboBox<String> type_field;
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
    private Button draft_button;
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
    private TextField tax_rate_field;
    @FXML
    private TextField total_tax_field;
    @FXML
    private TextField total_field;
    @FXML
    private TextArea notes_field;
    @FXML
    private TextField term_field;

}
