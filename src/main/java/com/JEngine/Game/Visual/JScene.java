package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Sprite;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

/**
 * @author Noah Freelove
 * @version 1
 * JScene is how you hold load all the objects in your scene into memory.
 *
 * scene.findObjectsByIdentity is a very useful method that allows you to pick out specific objects in a scene.
 * it is not very efficient though, so it should NEVER be called in an update function. Call it once and create a ref
 * to the object instead.
 */
public class JScene extends Thing {

    public JWindow window;
    public ObjRef[] sceneObjects;
    public JUIObject[] juiObjects;
    private int maxObjects;
    private String sceneName;

    /**
     * @param window Window that the scene will project to.
     * @param maxObjects Max objects allowed in the scene. You cannot change this number after the scene has been created.
     * @param sceneName Name of the scene. Can be changed with setSceneName(String newName)
     */
    public JScene(JWindow window, int maxObjects, String sceneName) {
        super(true);
        this.window = window;
        this.maxObjects = maxObjects;
        this.sceneName = sceneName;
        sceneObjects = new ObjRef[maxObjects];
        juiObjects = new JUIObject[maxObjects];
    }

    /**
     * Adds an object to the active scene
     * @param o Object to add to the scene
     */
    public void add(JObject o) {
        if (o == null || o.transform == null) {
            LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i] == null) {
                sceneObjects[i] = new ObjRef(o);
                LogInfo("Added object to scene " + ((sceneObjects[i] != null) ? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");
    }

    /**
     * Adds an UIObject to the active scene
     * @param o JUIObject to add to the scene
     */
    public void addUI(JUIObject o) {
        if (o == null || o.transform == null) {
            LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < juiObjects.length; i++) {
            if (juiObjects[i] == null) {
                juiObjects[i] = o;
                juiObjects[i].Start();
                LogInfo("Added UI element to scene " + ((juiObjects[i] != null) ? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");

    }

    public void runStartBehaviors()
    {
        for (ObjRef sceneObject : sceneObjects) {
            if (sceneObject != null) {
                if(sceneObject.objRef.getActive())
                {
                    sceneObject.objRef.Start();
                    try {
                        ((Sprite)sceneObject.objRef).collider.initializeCollider();
                    }catch (Exception ignore)
                    { /*ignore*/ }
                }
            }
        }
        for (JUIObject juiObject : juiObjects) {
            if (juiObject != null) {
                if(juiObject.getActive())
                    juiObject.Start();
            }
        }
    }



    /**
     * Loads a JScene from a .JScene file and overwrites all current scene content
     * @param filepath The filepath to the JScene file
     */
    public void loadFromFile(String filepath, JWindow window, String sceneName)
    {
        JScene createdScene = JSceneLoader.load(filepath,window,sceneName);

        maxObjects = createdScene.maxObjects;
        sceneObjects = createdScene.sceneObjects;
        juiObjects = createdScene.juiObjects;
        this.window = window;
        this.sceneName = sceneName;
    }

    // because objects can have the same name and tag, we must return an array of objects in the event of duplicates
    // findObjectsByIdentity can get you the name of objects you only have the tag for or vice versa

    /**
     * Find an object in the scene by Name, Tag, or Both
     * @param name The name of the object you would like to search for. Leave empty if not searching by name
     * @param tag The tag of the object you would like to search for. Leave empty if not searching by tag
     * @param searchType The type of search you would like to do. (SearchByName, SearchByTag, SearchByNameAndTag)
     * @return JObject reference from the scene
     */
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
        int i = 0;
        for (JObject o:
             sceneSize) {
            if(o != null)
            {
                searchResult[i] = o;
                i++;
            }
        }
        return searchResult;
    }

    /**
     * Transfers all objects in one scene to another
     * @param newScene The new JScene to migrate to
     * @param force If the new scene has a lower maxObjectCount, discard the objects that cannot fit in
     * @param switchSceneUponCompletion Automatically switch scenes when migration is complete?
     */
    public void migrateScene(JScene newScene, boolean force, boolean switchSceneUponCompletion)
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
            JSceneManager.setActiveScene(newScene);
            LogInfo("Successfully Migrated scene content and activated scene.");
            return;
        }
        LogInfo("Successfully Migrated scene content. do camera.setActiveScene(newScene) to switch to new scene");
    }

    /**
     * Returns maxObjects in the scene
     * @return maxObjects
     */
    public int getMaxObjects() {return maxObjects;}

    /**
     * Returns the scene's name
     * @return Scene's name
     */
    public String getSceneName() {return sceneName;}

    /**
     * Set the scene's name
     * @param newSceneName The new name of the scene
     */
    public void setSceneName(String newSceneName) {sceneName = newSceneName;}

    /**
     * Remove all objects from the scene
     * @param newMaxObjects set a new max objects value
     */
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
