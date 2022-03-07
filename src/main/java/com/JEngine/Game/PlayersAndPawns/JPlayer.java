package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Direction;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.Utility.Input;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

/** JEngine.Player (c) Noah Freelove
 * Brief Explanation:
 * A Player is form of pawn
 * **/

public class JPlayer extends JPawn {
    public JPlayer(Transform transform, JImage newSprite, JIdentity JIdentity) {
        super(transform, newSprite, JIdentity);
    }

    public void onKeyPressed(KeyCode key){}
    public void onKeyReleased(KeyCode key){}
}
