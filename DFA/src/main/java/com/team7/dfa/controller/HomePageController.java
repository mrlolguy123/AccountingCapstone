package com.team7.dfa.controller;

import com.team7.dfa.model.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * The HomePageController class controls the functionality of the home page of the DFA application.
 */
public class HomePageController extends ParentController {

    @FXML
    private ImageView graph1;

    @FXML
    private Button generateGraphsButton;

    /**
     * Handles the action event when the generateGraphsButton is clicked.
     * Currently, it updates the graph1 ImageView with the correct graph image.
     */
    @FXML
    private void generateGraphButton() {
        generateGraphsButton.setOnAction(event -> {
            Graph sample_graph = new Graph("select inv_state from dannyInvoiceRecords",
                    "inv_state",
                    "Invoice State Pie Chart");
            sample_graph.updateGraphImage(graph1);
        });
    }
}