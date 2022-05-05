package com.JEngine.Game.PlayersAndPawns;

import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Identity;
import javafx.scene.input.KeyCode;

/** 2022 - Noah Freelove
 * Brief Explanation:
 * A Player is form of pawn which has events for key presses
 * **/

public class Player extends Pawn {
    public Player(Transform transform, GameImage newSprite, Identity Identity) {
        super(transform, newSprite, Identity);
    }

    /**
     * Called when a supported key is pressed, see the keys supported: {@link com.JEngine.Utility.Input}
     * @param key The key that was pressed
     */
    public void onKeyPressed(KeyCode key){}

    /**
     * Called when a supported key is released, see the keys supported: {@link com.JEngine.Utility.Input}
     * @param key The key that was released
     */
    public void onKeyReleased(KeyCode key){}
}
