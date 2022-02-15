package com.JEngine.UserInterface;

import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class JButton extends JUIObject {
    Button button;
    public JButton(Transform transform, JIdentity JIdentity, Image defaultImage, int sizeX, int sizeY, int maxSizeX, int maxSizeY) {
        super(transform, JIdentity, defaultImage, sizeX, sizeY);
        this.button = new Button();
        this.button.setMaxSize(maxSizeX, maxSizeY);
        this.button.setMinSize(sizeX, sizeY);
        this.transform = transform;
        this.JIdentity = JIdentity;
        this.button.setText("yooooooooooooo");
    }
    public Button getButton() {return button;}
}
