package com.JEngine.TemplateObjects;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.Scenes.JSceneManager;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;

public class ScreenBorder extends JPawn {

    public ScreenBorder() {
        super(Transform.exSimpleTransform(0,0), null, new JIdentity("ScreenBorder", "border"));
        setCollider(new JBoxCollider(Transform.exSimpleTransform(0,0), new JIdentity("ScreenBorderWall1", "border"),
                (int)(1280*JSceneManager.getWindow().getScaleMultiplier()),
                10, this, false));
        JPawn wall2 = new JPawn(Transform.exSimpleTransform(0,(int)(720*JSceneManager.getWindow().getScaleMultiplier()), null);new JIdentity("ScreenBorderWall2", "border")
    }
}
