package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Identity;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;

/** MousePointer (c) Noah Freelove
 * Brief Explanation:
 * MousePointer overrides the default mouse image and allows for tracking its exact position in the window, and key presses.
 * **/

public class MousePointer extends Player {
    GameImage cursorIcon;
    private double posX;
    private double posY;

    private boolean leftMouseDown;
    private boolean rightMouseDown;
    private boolean cameraFollowOffset;

    /**
     * Create a new cursor
     * @param cursorIcon The image of the cursor
     */
    public MousePointer(GameImage cursorIcon) {
        super(Transform.exSimpleTransform(0,0), null, new Identity("MousePointer", "pointer"));
        this.cursorIcon = cursorIcon;
        init();
    }

    public MousePointer(GameImage cursorIcon, boolean cameraFollowOffset) {
        super(Transform.exSimpleTransform(0,0), null, new Identity("MousePointer", "pointer"));
        this.cursorIcon = cursorIcon;
        this.cameraFollowOffset = cameraFollowOffset;
        init();
    }

    /**
     * Init the scene's cursor
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

        scene.setOnMouseDragged(mouseEvent -> {
            posX = mouseEvent.getX();
            posY = mouseEvent.getY();
        });

        scene.setOnMousePressed(mouseEvent -> {
            if(mouseEvent.isPrimaryButtonDown())
            {
                leftMouseDown = true;
                onMousePressed(true);

            }
            if (mouseEvent.isSecondaryButtonDown())
            {
                rightMouseDown = true;
                onMousePressed(false);
            }
        });
        scene.setOnMouseReleased(mouseEvent -> {
            if(!mouseEvent.isPrimaryButtonDown() && leftMouseDown)
            {
                leftMouseDown = false;
                onMouseReleased(true);

            }
            if (!mouseEvent.isSecondaryButtonDown() && isRightMouseDown())
            {
                rightMouseDown = false;
                onMouseReleased(false);
            }
        });
        scene.setOnScroll(mouseEvent -> {
            onScroll(mouseEvent.getDeltaY());
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
    protected void onMousePressed(boolean isLeftDown){}

    /**
     * Override this method to set events when the mouse is released
     */
    protected void onMouseReleased(boolean isLeftDown){}

    @Override
    public void Update() {
        super.Update();
    }

    public Vector2 pointerPosToWorldPoint(){

        // mouse position
        double x = posX;
        double y = posY;
        GameCamera camera = SceneManager.getActiveCamera();
        // factor in camera position
        x += camera.getPosition().x;
        y += camera.getPosition().y;

        return new Vector2((float) x, (float) y);
    }

    public boolean cameraFollowOffset() {
        return cameraFollowOffset;
    }

    public void setCameraFollowOffset(boolean cameraFollowOffset) {
        this.cameraFollowOffset = cameraFollowOffset;
    }

    public boolean isLeftMouseDown() {
        return leftMouseDown;
    }

    public boolean isRightMouseDown() {
        return rightMouseDown;
    }
    public void onScroll(double scroll){}
}
