package com.JEngine.TemplateObjects;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Identity;
/** BackgroundImage
 *  A background image that can be used in a scene.
 *  Automatically takes the full size of the window
 * */
public class BackgroundImage extends Sprite {
    public BackgroundImage(String filePath) {
        super(Transform.simpleTransform(0,0,-100), new GameImage(true, filePath, (int)(SceneManager.getWindow().getStage().getWidth()* SceneManager.getWindow().getScaleMultiplier()), (int)(SceneManager.getWindow().getStage().getHeight()* SceneManager.getWindow().getScaleMultiplier())), new Identity("BackgroundImage","backgroundImage"));
    }
}
