package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

// JScene holds list all objects in scene
public class JScene extends Thing {
    public JWindow window;
    public ObjRef[] sceneObjects;
    private int maxObjects;

    public JScene(JWindow window, int maxObjects) {
        super(true);
        this.window = window;
        this.maxObjects = maxObjects;
        sceneObjects = new ObjRef[maxObjects];
    }

    public void add(Object o)
    {
        if(o == null || o.transform == null)
        {
            super.LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < sceneObjects.length; i++) {
            if(sceneObjects[i] == null)
            {
                sceneObjects[i] = new ObjRef(o);

                super.LogInfo("Added object to scene " + ((sceneObjects[i] != null)? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        super.LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");

    }

    // very inefficent as of right now
    public Object[] getObjectsByTag(String tag) {

        int count = 0;
        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i].objRef.JIdentity.compareTag(tag))
                count++;
        }
        // if 0 matches, don't bother looking through all scene objects again
        if (count == 0) { return new Object[0]; }

        Object[] tmpArr = new Object[count];
        for (int i = 0; i < sceneObjects.length; i++) {
            if(sceneObjects[i].objRef.JIdentity.compareTag(tag))
                tmpArr[i] = sceneObjects[i].objRef;
        }

        return tmpArr;
    }
    public int getMaxObjects() {return maxObjects;}
}
