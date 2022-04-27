package com.JEngine.TemplateObjects;

import com.JEngine.Game.PlayersAndPawns.Colliders.JBoxCollider;
import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

/** ScreenBorder
 * Create a hard collection of JBoxColliders to create a border around the screen.
 */
public class ScreenBorder {

    public ScreenBorder(Vector3 basePos){
        createWalls(basePos);
    }

    void createWalls(Vector3 basePos){
        JPawn wall1 = new JPawn(Transform.simpleTransform(basePos),null,
                new JIdentity("ScreenBorderWall1", "border"));

        wall1.setCollider(new JBoxCollider(Transform.simpleTransform(basePos), new JIdentity("ScreenBorderWallTop", "border"),
                (int)(1280* SceneManager.getWindow().getScaleMultiplier()),
                1, wall1, false));

        JPawn wall2 = new JPawn(Transform.exSimpleTransform(basePos.x,basePos.y + (int)(720* SceneManager.getWindow().getScaleMultiplier())),null,
                new JIdentity("ScreenBorderWall2", "border"));
        wall2.setCollider(new JBoxCollider(wall2.getTransform(), new JIdentity("ScreenBorderWallBottom", "border"),
                (int)(1280* SceneManager.getWindow().getScaleMultiplier()),
                1, wall2, false));

        JPawn wall3 = new JPawn(Transform.exSimpleTransform(basePos.x + (int)(1280* SceneManager.getWindow().getScaleMultiplier()), basePos.y),null,
                new JIdentity("ScreenBorderWall3", "border"));
        wall3.setCollider(new JBoxCollider(wall3.getTransform(), new JIdentity("ScreenBorderWallRight", "border"),
                1,
                (int)(720* SceneManager.getWindow().getScaleMultiplier()), wall3, false));

        JPawn wall4 = new JPawn(Transform.exSimpleTransform(basePos.x, basePos.y),null,
                new JIdentity("ScreenBorderWall4", "border"));
        wall4.setCollider(new JBoxCollider(wall4.getTransform(), new JIdentity("ScreenBorderWallLeft", "border"),
                1,
                (int)(720* SceneManager.getWindow().getScaleMultiplier()), wall4, false));

        SceneManager.getActiveScene().add(wall1);
        SceneManager.getActiveScene().add(wall2);
        SceneManager.getActiveScene().add(wall3);
        SceneManager.getActiveScene().add(wall4);
    }
}
