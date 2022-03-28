package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import javafx.scene.input.KeyCode;

/** com.JEngine.Player (c) Noah Freelove
 * Brief Explanation:
 * A Player is form of pawn which has events for key presses
 * **/

public class JPlayer extends JPawn {
    public JPlayer(Transform transform, JImage newSprite, JIdentity JIdentity) {
        super(transform, newSprite, JIdentity);
    }

    public void onKeyPressed(KeyCode key){}
    public void onKeyReleased(KeyCode key){}
}
