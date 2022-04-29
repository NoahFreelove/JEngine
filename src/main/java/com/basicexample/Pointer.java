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
    FlipFlop flipFlop = new FlipFlop();

    @Override
    protected void onMouseReleased(){
        if(flipFlop.getState())
        {
            SceneManager.getActiveCamera().setZoom(new Vector2(1,1));
        }
        else
        {
            SceneManager.getActiveCamera().setZoom(new Vector2(2,2));
        }
    }


}
