package com.JEngine.UserInterface;

import com.JEngine.PrimitiveTypes.JImage;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;


public class JPointer {
    JImage cursorIcon;

    public JPointer(JImage cursorIcon) {
        this.cursorIcon = cursorIcon;
    }

    public void setWindowCursor(Scene scene)
    {
        scene.setCursor(new ImageCursor(cursorIcon.getImage()));
    }
}
