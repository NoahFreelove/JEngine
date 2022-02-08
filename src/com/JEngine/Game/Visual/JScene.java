package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

// JScene holds list all objects in scene
public class JScene extends Thing {
    public JWindow window;
    public Object[] sceneObjects;
    public JScene(JWindow window, int maxObjects) {
        super(true);
        this.window = window;
        sceneObjects = new Object[maxObjects];
    }

    public void add(Object o)
    {
        for (int i = 0; i < sceneObjects.length; i++) {
            if(sceneObjects[i] == null)
            {
                sceneObjects[i] = o;
                super.LogInfo(String.valueOf((sceneObjects[i] != null)));
                break;
            }
        }
    }
}
