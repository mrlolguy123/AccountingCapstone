package com.team7.dfa.controller;

import com.team7.dfa.db.DatabaseConnector;
import com.team7.dfa.model.Graph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.util.logging.Logger;

/**
 * The HomePageController class controls the functionality of the home page of the DFA application.
 */
public class HomePageController extends ParentController {

    @FXML
    private AnchorPane contentPane;

    static Logger log = null;

    @FXML
    private ImageView invoiceFlowGraph;


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
            Graph sample_graph = new Graph("select inv_state from dannyInvoiceRecords",
                    "inv_state",
                    "Invoice State Pie Chart");
            sample_graph.updateGraphImage(invoiceFlowGraph);
//            profitGraph.setPreserveRatio(true);
//            profitGraph.setFitWidth(profitGraph.getFitWidth());
//            profitGraph.setFitHeight(profitGraph.getFitHeight());
    }
}