package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.GameImage;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

/** JPointer (c) Noah Freelove
 * Brief Explanation:
 * JPointer overrides the default mouse image and allows for tracking its exact position in the window, and key presses.
 * **/

public class MousePointer {
    GameImage cursorIcon;
    private double posX;
    private double posY;

    /**
     * Create a new cursor
     * @param cursorIcon The image of the cursor
     */
    public MousePointer(GameImage cursorIcon) {
        this.cursorIcon = cursorIcon;
    }

    /**
     * Set the scene of the cursor
     * @param scene JavaFX scene. Can be obtained from JSceneManager.getWindow().getStage().getScene()
     */
    public void setWindowCursor(Scene scene)
    {
        scene.setCursor(new ImageCursor(cursorIcon.getImage()));
        scene.setOnMouseMoved(event -> {
            posX = event.getX();
            posY = event.getY();
        });

        scene.setOnMousePressed(mouseEvent -> {
            onMousePressed();
        });
        scene.setOnMouseReleased(mouseEvent -> {
            onMouseReleased();
        });
    }

    /**
     * Get x position of the cursor
     * @return x position of the cursor
     */
    public double getX(){return posX;}

    /**
     * Get y position of the cursor
     * @return y position of the cursor
     */
    public double getY(){return posY;}

    /**
     * Override this method to set events when the mouse is pressed
     */
    protected void onMousePressed(){}

    /**
     * Override this method to set events when the mouse is released
     */
    protected void onMouseReleased(){}
}
