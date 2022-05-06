module com.example.jengine {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.basicexample to javafx.fxml;
    exports com.basicexample;
    exports com.JEngine.Components;
    opens com.JEngine.Components to javafx.fxml;
}