package com.basicexample;

import com.JEngine.Game.Visual.MousePointer;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.FlipFlop;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.Vector2;

public class Pointer extends MousePointer {
    /**
     * Create a new cursor
     *
     * @param cursorIcon The image of the cursor
     */
    public Pointer(GameImage cursorIcon) {
        super(cursorIcon);
    }

    public Pointer(GameImage cursorIcon, boolean cameraFollowOffset) {
        super(cursorIcon, cameraFollowOffset);
    }

    @Override
    protected void onMousePressed(){

    }


}
