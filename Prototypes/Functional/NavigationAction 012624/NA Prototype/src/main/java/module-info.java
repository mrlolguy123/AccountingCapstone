module com.example.napr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.napr to javafx.fxml;
    exports com.example.napr;
}