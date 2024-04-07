package com.team7.dfa.controller;

import com.team7.dfa.db.DatabaseConnector;
import com.team7.dfa.model.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.logging.Logger;

/**
 * The HomePageController class controls the functionality of the home page of the DFA application.
 */
public class HomePageController extends ParentController {

    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DatabaseConnector.class.getName());
    }

    @FXML
    private ImageView profitGraph;

    @FXML
    private ImageView invoiceFlowGraph;

    @FXML
    private Button generateGraphsButton;

    /**
     * Handles the action event when the generateGraphsButton is clicked.
     * Currently, it updates the graph1 ImageView with the correct graph image.
     */
    @FXML
    private void generateGraphButton() {
        generateGraphsButton.setOnAction(event -> {
            log.info("Generating Graphs");
            Graph sample_graph = new Graph("select inv_state from dannyInvoiceRecords",
                    "inv_state",
                    "Invoice State Pie Chart");
            sample_graph.updateGraphImage(invoiceFlowGraph);
//            profitGraph.setPreserveRatio(true);
//            profitGraph.setFitWidth(profitGraph.getFitWidth());
//            profitGraph.setFitHeight(profitGraph.getFitHeight());
        });
    }
}