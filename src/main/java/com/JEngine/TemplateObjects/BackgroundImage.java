package com.JEngine.TemplateObjects;

import com.JEngine.Game.PlayersAndPawns.JSprite;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
/** BackgroundImage
 *  A background image that can be used in a scene.
 *  Automatically takes the full size of the window
 * */
public class BackgroundImage extends JSprite {
    public BackgroundImage(String filePath) {
        super(Transform.simpleTransform(0,0,-100), new JImage(true, filePath, (int)(JSceneManager.getWindow().getStage().getWidth()*JSceneManager.getWindow().getScaleMultiplier()), (int)(JSceneManager.getWindow().getStage().getHeight()*JSceneManager.getWindow().getScaleMultiplier())), new JIdentity("BackgroundImage","backgroundImage"));
    }
}
