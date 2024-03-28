package com.team7.dfa.controller;

import com.team7.dfa.model.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class HomePageController extends ParentController {

    @FXML
    private ImageView graph1;

    @FXML
    private ImageView graph2;

    @FXML
    private Button generateGraphsButton;

    @FXML
    private void generateGraphButton() {
        generateGraphsButton.setOnAction(event -> {
            String sqlCommand = "select inv_state from dannyInvoiceRecords";
            String graphChoice = "4";
            String x_name = "inv_state";
            String y_name = "";
            String graphName = "Invoice State Pie Chart";
            Graph sample_graph = new Graph(sqlCommand, graphChoice, x_name, graphName);
            sample_graph.updateGraphImage(graph1);
            System.out.println("Successfully Executed");
        });
    }
}