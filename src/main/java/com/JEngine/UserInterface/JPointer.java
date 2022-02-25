package com.JEngine.UserInterface;

import com.JEngine.PrimitiveTypes.JImage;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;


public class JPointer {
    JImage cursorIcon;
    private double posX;
    private double posY;

    public JPointer(JImage cursorIcon) {
        this.cursorIcon = cursorIcon;
    }

    public void setWindowCursor(Scene scene)
    {
        scene.setCursor(new ImageCursor(cursorIcon.getImage()));
        scene.setOnMouseMoved(event -> {
            posX = event.getX();
            posY = event.getY();
        });
    }

    public double getX(){return posX;}
    public double getY(){return posY;}

}
