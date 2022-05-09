package com.JEngine.TemplateObjects;

import com.JEngine.Components.Colliders.BoxCollider_Comp;
import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Core.Identity;

/** ScreenBorder
 * Create 4 hard Box colliders to create a border around the screen from a given position.
 */
public class ScreenBorder {

    public ScreenBorder(Vector3 basePos){
        createWalls(basePos);
    }

    void createWalls(Vector3 basePos){
        int scaleX = (int)(1280* SceneManager.getWindow().getScaleMultiplier());
        int scaleY = (int)(720* SceneManager.getWindow().getScaleMultiplier());
        GameObject parent = new GameObject(Transform.simpleTransform(basePos), new Identity("ScreenBorder", "ScreenBorder"));
        //Top wall
        BoxCollider_Comp wall1 = new BoxCollider_Comp(new Vector3(0,0,0),scaleX,1,false,parent);
        // Bottom wall
        BoxCollider_Comp wall2 = new BoxCollider_Comp(new Vector3(0,720-35,0),1280,1,false,parent);
        // Left wall
        BoxCollider_Comp wall3 = new BoxCollider_Comp(new Vector3(0,0,0),1,scaleY,false,parent);
        // Right wall
        BoxCollider_Comp wall4 = new BoxCollider_Comp(new Vector3(1280,0,0),1,scaleY,false,parent);



        parent.addComponent(wall1);
        parent.addComponent(wall2);
        parent.addComponent(wall3);
        parent.addComponent(wall4);
        SceneManager.getActiveScene().add(parent);
    }
}
