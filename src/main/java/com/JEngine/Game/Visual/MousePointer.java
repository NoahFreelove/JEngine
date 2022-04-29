package com.JEngine.Game.Visual;

import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector2;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.GameObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Identity;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

/** JPointer (c) Noah Freelove
 * Brief Explanation:
 * JPointer overrides the default mouse image and allows for tracking its exact position in the window, and key presses.
 * **/

public class MousePointer extends GameObject {
    GameImage cursorIcon;
    private double posX;
    private double posY;

    /**
     * Create a new cursor
     * @param cursorIcon The image of the cursor
     */
    public MousePointer(GameImage cursorIcon) {
        super(Transform.exSimpleTransform(0,0), new Identity("MousePointer", "pointer"));
        this.cursorIcon = cursorIcon;
        init();
    }

    /**
     * Init the scene's cursor'
     */
    private void init()
    {
        Scene scene = SceneManager.getWindow().scene;
        if(cursorIcon != null)
        {
            scene.setCursor(new ImageCursor(cursorIcon.getImage()));
        }
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

    @Override
    public void Update() {
        Vector2 pos = pointerPosToWorldPoint();
        setPosition(new Vector3(pos.x, pos.y, 0));
        System.out.println(pos);
    }

    public Vector2 pointerPosToWorldPoint(){
        // factor in zoom level, window scale, pointer position, and camera position
        double x = (SceneManager.getActiveCamera().getPosition().x + posX) * (1/SceneManager.getActiveCamera().getZoom().x*SceneManager.getActiveCamera().getZoom().x);
        double y = (SceneManager.getActiveCamera().getPosition().y + posY) * (1/SceneManager.getActiveCamera().getZoom().y*SceneManager.getActiveCamera().getZoom().x);
        return new Vector2((float) x, (float) y);
    }
}
