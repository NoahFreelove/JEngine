package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.JWindow;
import com.JEngine.Game.Visual.SearchType;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

import java.io.IOException;
import java.net.URL;

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

    public ObjRef[] sceneObjects;
    private int maxObjects;
    private String sceneName;

    public Group uiObjects = new Group();
    private Group loadedUI = new Group();
    public URL uiURL = null;

    /**
     * @param maxObjects Max objects allowed in the scene. You cannot change this number after the scene has been created.
     * @param sceneName Name of the scene. Can be changed with setSceneName(String newName)
     */
    public JScene(int maxObjects, String sceneName) {
        super(true);
        this.maxObjects = maxObjects;
        this.sceneName = sceneName;
        sceneObjects = new ObjRef[maxObjects];
    }

    public JScene(int maxObjects, String sceneName, URL url) {
        super(true);
        setUiURL(url);
        try {
            loadedUI = FXMLLoader.load(uiURL);
        } catch (IOException e) {
            //ignore
        }

        loadUI();
        this.maxObjects = maxObjects;
        this.sceneName = sceneName;
        sceneObjects = new ObjRef[maxObjects];
    }

    public void loadUI()
    {
        if(uiURL == null)
        {
            return;
        }
        uiObjects = loadedUI;
    }

    public void setUiURL(URL newURl)
    {
        uiURL = newURl;
    }

    private boolean sceneHasRoom()
    {
        for (ObjRef sceneObject : sceneObjects) {
            if (sceneObject == null)
                return true;
        }
        return false;
    }

    // quicksort implementation, GitHub copilot helped with this one
    public void sortByZ(){
        ObjRef[] temp = new ObjRef[maxObjects];
        int i = 0;
        for (ObjRef o: sceneObjects) {
            temp[i] = o;
            i++;
        }
        quickSortZ(temp, 0, maxObjects-1);
        sceneObjects = temp;
    }

    public void quickSortZ(ObjRef arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSortZ(arr, begin, partitionIndex-1);
            quickSortZ(arr, partitionIndex+1, end);
        }
    }

    private int partition(ObjRef arr[], int begin, int end) {
        if(arr[end] == null)
            return end;
        int pivot = (int)arr[end].objRef.transform.position.z;
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if(arr[j] == null)
            {
                i++;
                continue;
            }

            if ((int)arr[j].objRef.transform.position.z <= pivot) {
                i++;

                ObjRef swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        ObjRef swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
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
                LogExtra(String.format("Added '%s' (%s) to the scene Successfully", o.JIdentity.getName(), o.getClass().getSimpleName()));
                sortByZ();
                return;
            }
            else if(sceneObjects[i].objRef.isQueuedForDeletion() && !sceneHasRoom())
            {
                sceneObjects[i] = new ObjRef(o);
                LogExtra(String.format("Overwrote object queued for deletion. Added '%s' (%s) to the scene Successfully", o.JIdentity.getName(), o.getClass().getSimpleName()));
                sortByZ();
                return;
            }
        }
        LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");
    }

    /**
     * Remove a JObject from the scene.
     * Note: Removing a JObject from the scene just stops it from being rendered and any update functions being called
     * on it. It is not cleared from memory just yet...
     * It will be the first in line to be overwritten when there is no more space in the scene.
     *
     * Because of the soft way its being 'removed' from the scene, you are able to unDelete() an object if you have
     * its name and tag, or reference
     * @param o The JObject to remove
     */
    public void remove(JObject o)
    {
        o.setQueuedForDeletion(true);
        LogExtra(String.format("Queued object '%s' (%s) for deletion.", o.JIdentity.getName(), o.getClass().getSimpleName()));
    }

    /**
     * Attempt to restore an object Queued For Deletion
     * @param o Object to restore
     */
    public void unDelete(JObject o)
    {
        for (ObjRef ref :
                sceneObjects) {
            if(ref.objRef == o && ref.objRef.isQueuedForDeletion())
            {
                o.setQueuedForDeletion(false);
                LogExtra(String.format("Un-deleted '%s' (%s)", o.JIdentity.getName(), o.getClass().getSimpleName()));
                return;
            }
        }
        LogExtra(String.format("Could not Un-delete '%s' (%s)", o.JIdentity.getName(), o.getClass().getSimpleName()));
    }
    /**
     * Attempt to restore an object Queued For Deletion
     * @param identity Search scene for identity to restore
     */
    public void unDelete(JIdentity identity)
    {
        for (ObjRef ref :
                sceneObjects) {
            if(ref.objRef.JIdentity.toString().equals(identity.getName() + " : " + identity.getTag()) && ref.objRef.isQueuedForDeletion())
            {
                ref.objRef.setQueuedForDeletion(false);
                LogExtra(String.format("Un-deleted '%s' (%s)", ref.objRef.JIdentity.getName(), ref.objRef.getClass().getSimpleName()));
                return;
            }
        }
        LogExtra(String.format("Could not Un-delete '%s'", identity.getName()));

    }

    public void runStartBehaviors()
    {
        for (ObjRef sceneObject : sceneObjects) {
            if (sceneObject != null) {
                if(sceneObject.objRef.getActive())
                {
                    sceneObject.objRef.Start();
                    try {
                        //((JPawn)sceneObject.objRef).getCollider().initializeCollider();
                    }catch (Exception ignore)
                    { /*ignore*/ }
                }
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
