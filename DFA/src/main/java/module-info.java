module com.team7.dfa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.team7.dfa to javafx.fxml;
    exports com.team7.dfa;
    exports com.team7.dfa.controller;
    opens com.team7.dfa.controller to javafx.fxml;
}