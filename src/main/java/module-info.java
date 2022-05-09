module com.example.jengine {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    exports com.JEngine.Components;
    opens com.JEngine.Components to javafx.fxml;
    opens com.Examples.MovingSquare to javafx.graphics;
    exports com.JEngine.Components.Colliders;
    opens com.JEngine.Components.Colliders to javafx.fxml;
}