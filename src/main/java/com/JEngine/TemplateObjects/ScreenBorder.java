package com.JEngine.TemplateObjects;

import com.JEngine.Game.PlayersAndPawns.Colliders.BoxCollider;
import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Core.Identity;

/** ScreenBorder
 * Create a hard collection of JBoxColliders to create a border around the screen.
 */
public class ScreenBorder {

    public ScreenBorder(Vector3 basePos){
        createWalls(basePos);
    }

    void createWalls(Vector3 basePos){
        Pawn wall1 = new Pawn(Transform.simpleTransform(basePos),null,
                new Identity("ScreenBorderWall1", "border"));

        wall1.setCollider(new BoxCollider(Transform.simpleTransform(basePos), new Identity("ScreenBorderWallTop", "border"),
                (int)(1280* SceneManager.getWindow().getScaleMultiplier()),
                1, wall1, false));

        Pawn wall2 = new Pawn(Transform.exSimpleTransform(basePos.x,basePos.y + (int)(720* SceneManager.getWindow().getScaleMultiplier())),null,
                new Identity("ScreenBorderWall2", "border"));
        wall2.setCollider(new BoxCollider(wall2.getTransform(), new Identity("ScreenBorderWallBottom", "border"),
                (int)(1280* SceneManager.getWindow().getScaleMultiplier()),
                1, wall2, false));

        Pawn wall3 = new Pawn(Transform.exSimpleTransform(basePos.x + (int)(1280* SceneManager.getWindow().getScaleMultiplier()), basePos.y),null,
                new Identity("ScreenBorderWall3", "border"));
        wall3.setCollider(new BoxCollider(wall3.getTransform(), new Identity("ScreenBorderWallRight", "border"),
                1,
                (int)(720* SceneManager.getWindow().getScaleMultiplier()), wall3, false));

        Pawn wall4 = new Pawn(Transform.exSimpleTransform(basePos.x, basePos.y),null,
                new Identity("ScreenBorderWall4", "border"));
        wall4.setCollider(new BoxCollider(wall4.getTransform(), new Identity("ScreenBorderWallLeft", "border"),
                1,
                (int)(720* SceneManager.getWindow().getScaleMultiplier()), wall4, false));

        SceneManager.getActiveScene().add(wall1);
        SceneManager.getActiveScene().add(wall2);
        SceneManager.getActiveScene().add(wall3);
        SceneManager.getActiveScene().add(wall4);
    }
}
