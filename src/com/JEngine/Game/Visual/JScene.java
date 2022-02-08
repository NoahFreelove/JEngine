package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

// JScene holds list all objects in scene
public class JScene extends Thing {
    public JWindow window;
    public ObjRef[] sceneObjects;
    public JScene(JWindow window, int maxObjects) {
        super(true);
        this.window = window;
        sceneObjects = new ObjRef[maxObjects];
    }

    public void add(Sprite o)
    {
        if(o == null || o.transform == null)
        {
            super.LogWarning("Tried to add null object to scene!");
            return;
        }
        for (int i = 0; i < sceneObjects.length; i++) {
            if(sceneObjects[i] == null)
            {
                sceneObjects[i] = new ObjRef(o, o.transform.position);

                super.LogInfo("Added object to scene successfully? " + ((sceneObjects[i] != null)? "Yes" : "No"));
                break;
            }
        }
    }
}
