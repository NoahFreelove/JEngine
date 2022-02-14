module com.example.jengine {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example to javafx.fxml;
    exports com.example;
}