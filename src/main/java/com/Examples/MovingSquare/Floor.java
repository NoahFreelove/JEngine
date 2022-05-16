package com.Examples.MovingSquare;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Sprite;

import java.io.File;

public class Floor extends Sprite {

    public Floor(int size, Vector3 initPos) {
        super(new Transform(initPos, new Vector3(0,0,0), new Vector3(1,1,1)), new GameImage(new File("bin/wall.png").getAbsolutePath(), size*128,64), new Identity("Floor", "wall"));
        addComponents(new BoxCollider_Comp(new Vector3(0,0,0), size*128, 64, false, this), new DontDestroyOnLoad_Comp());
    }
}
