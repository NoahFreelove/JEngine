package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

/** JEngine.JScene (c) Noah Freelove
 * Brief Explanation:
 * JScene is how you hold load all the objects in your scene into memory.
 * Use scene.add(Object) to add an object into the scene. The camera uses sceneObjects[] to determine what to render
 *
 * maxObjects is final and cannot be changed mid-gameplay.
 * to change the max objects create a new scene with a higher object count and transfer the objects over with
 * scene.migrateScene
 *
 * scene.findObjectsByIdentity is a very useful method that allows you to pick out specific objects in a scene.
 * it is not very efficient though, so it should NEVER be called in an update function. Call it once and create a ref
 * to the object instead.
 * **/
public class JScene extends Thing {
    public JWindow window;
    public ObjRef[] sceneObjects;
    public JUIObject[] juiObjects;
    private final int maxObjects;
    private String sceneName;

    public JScene(JWindow window, int maxObjects, String sceneName) {
        super(true);
        this.window = window;
        this.maxObjects = maxObjects;
        this.sceneName = sceneName;
        sceneObjects = new ObjRef[maxObjects];
        juiObjects = new JUIObject[maxObjects];
    }

    public void add(JObject o)
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
                sceneObjects[i].objRef.Start();
                super.LogInfo("Added object to scene " + ((sceneObjects[i] != null)? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        super.LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");
    }
    public void addUI(JUIObject o)
    {
        if(o == null || o.transform == null)
        {
            super.LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < juiObjects.length; i++) {
            if(juiObjects[i] == null)
            {
                juiObjects[i] = o;
                juiObjects[i].Start();
                super.LogInfo("Added UI element to scene " + ((juiObjects[i] != null)? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        super.LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");

    }

    // because objects can have the same name and tag, we must return an array of objects in the event of duplicates
    // findObjectsByIdentity can get you the name of objects you only have the tag for or vice versa

    public JObject[] findObjectsByIdentity(String name, String tag, SearchType searchType) {
        int count = 0;
        JObject[] sceneSize = new JObject[maxObjects];

        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i] != null) {

                if (searchType == SearchType.SearchByName) {
                    if (sceneObjects[i].objRef.JIdentity.compareName(name)) {
                        sceneSize[i] = sceneObjects[i].objRef;
                        count++;
                    }
                } else if (searchType == SearchType.SearchByTag) {
                    if (sceneObjects[i].objRef.JIdentity.compareTag(tag)) {
                        sceneSize[i] = sceneObjects[i].objRef;
                        count++;
                    }
                } else {
                    if (sceneObjects[i].objRef.JIdentity.compareTag(tag) && sceneObjects[i].objRef.JIdentity.compareName(name)) {
                        sceneSize[i] = sceneObjects[i].objRef;
                        count++;
                    }
                }
            }
        }

        JObject[] searchResult = new JObject[count];
        System.arraycopy(sceneSize, 0, searchResult, 0, count);
        return searchResult;
    }
    public void migrateScene(JScene newScene, boolean force, boolean switchSceneUponCompletion, JCamera camera)
    {
        if((newScene.maxObjects < maxObjects) && !force)
        {
            LogWarning("CANNOT MIGRATE TO NEW SCENE: NEW SCENE HAS LOWER MAX OBJECT COUNT AND WOULD DESTROY OBJECTS" +
                    " USE force=true TO BYPASS THIS WARNING");
            return;
        }
        for (int i = 0; i < newScene.maxObjects; i++) {
            try{
                newScene.sceneObjects[i] = sceneObjects[i];
            }
            catch (Exception e)
            {
                break;
            }
        }
        if(switchSceneUponCompletion)
        {
            camera.setActiveScene(newScene);
            LogInfo("Successfully Migrated scene content and activated scene.");
            return;
        }
        LogInfo("Successfully Migrated scene content. do camera.setActiveScene(newScene) to switch to new scene");
    }
    public int getMaxUIObjects() {return juiObjects.length;}

    public int getMaxObjects() {return maxObjects;}
    public String getSceneName() {return sceneName;}

    public void setSceneName(String newSceneName) {sceneName = newSceneName;}

    public void purge(int newMaxObjects)
    {
        if(newMaxObjects<0)
        {
            newMaxObjects = maxObjects;
        }
        LogInfo(String.format("Purged scene: '%s' of ALL contents.", getSceneName()));
        sceneObjects = new ObjRef[newMaxObjects];
    }
}
