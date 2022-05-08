module com.example.jengine {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    exports com.JEngine.Components;
    opens com.JEngine.Components to javafx.fxml;
    exports com.Examples.MovingSquares;
    opens com.Examples.MovingSquares to javafx.fxml;
    exports com.Examples.AudioTest;
    opens com.Examples.AudioTest to javafx.fxml;
}