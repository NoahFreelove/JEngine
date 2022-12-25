package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Core.Component;
import com.JEngine.Core.GameObject;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Thing;
import com.JEngine.Game.Visual.SearchType;

import com.JEngine.Utility.ImageProcessingAndEffects.GameLight;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.JEngine.Game.Visual.Scenes.SceneQuicksort.quickSortZ;

/** 2022 - Noah Freelove
 * Brief Explanation:
 * GameScene is how you hold and load all the objects in your scene into memory.
 * Use SceneManager to switch the scene.
 * When a scene is not active, the update method will not run on any of the object in the scene, and it will not be rendered.
 * You can switch between scenes as much as you'd like, scene size may affect how quickly it loads.
 */
public class GameScene extends Thing {
    // Objects in the scene
    private GameObject[] sceneObjects;

    // The scene's name
    private String sceneName;

    // The UI Group
    public Group uiObjects = new Group();

    // The URL to the FXML UI file
    public URL uiURL = null;

    private CopyOnWriteArrayList<GameLight> lights = new CopyOnWriteArrayList<>();
    public Effect lightEffect;
    private boolean enableLighting = false;

    /**
     * @param sceneDefaultSize initial size of scene array. Not crucial as during play it will increase in size accordingly.
     * @param sceneName Name of the scene. Can be changed with setSceneName(String newName)
     */
    public GameScene(int sceneDefaultSize, String sceneName) {
        super(true);
        this.sceneName = sceneName;
        sceneObjects = new GameObject[sceneDefaultSize];
        addLight(new GameLight(new Lighting(new Light.Distant(50,50, Color.rgb(255,255,255,0.5))), false));
        generateLightEffect();
    }

    public GameScene() {
        super(true);
        this.sceneName = "Scene";
        sceneObjects = new GameObject[25];
        addLight(new GameLight(new Lighting(new Light.Distant(50,50, Color.rgb(255,255,255,0.5))), false));
        generateLightEffect();
    }
    /**
     * @param sceneName Name of the scene. Can be changed with setSceneName(String newName)
     */
    public GameScene(String sceneName) {
        super(true);
        this.sceneName = sceneName;
        sceneObjects = new GameObject[25];
        addLight(new GameLight(new Lighting(new Light.Distant(50,50, Color.rgb(255,255,255,0.5))), false));
        generateLightEffect();
    }

    /**
     * @param sceneName Name of the scene. Can be changed with setSceneName(String newName)
     * @param url URL to the FXML file
     */
    public GameScene(int sceneDefaultSize, String sceneName, URL url) {
        super(true);
        setUiURL(url);
        try {
            Group loadedUI = FXMLLoader.load(uiURL);
            loadUI(loadedUI);
        } catch (IOException e) {
            LogError("Couldn't load scene UI from file: " + url.toString());
        }

        this.sceneName = sceneName;
        sceneObjects = new GameObject[sceneDefaultSize];
        addLight(new GameLight(new Lighting(new Light.Distant(50,50, Color.rgb(255,255,255,0.5))), false));
        generateLightEffect();
    }

    /**
     * Load UI from loaded FXML file
     */
    public void loadUI(Group loadedUI)
    {
        if(uiURL == null)
        {
            return;
        }
        uiObjects = loadedUI;
    }

    /**
     * set the UI URL
     * @param newURl The new URL
     */
    public void setUiURL(URL newURl)
    {
        uiURL = newURl;
    }

    /**
     * Check if the scene has null space
     * @return True if the scene has null space
     */
    private boolean sceneHasRoom()
    {
        for (GameObject sceneObject : sceneObjects) {
            if (sceneObject == null)
                return true;
        }
        return false;
    }

    /**
     * Sort objects by z position, so they don't overlap in the wrong way
     */
    public void sortByZ(){
        // create instance of Object array
        GameObject[] temp = new GameObject[sceneObjects.length];
        int i = 0;
        for (GameObject o: sceneObjects) {
            temp[i] = o;
            i++;
        }
        quickSortZ(temp, 0, sceneObjects.length-1);
        sceneObjects = temp;
    }

    /**
     * Adds an object to the active scene.
     * Will over-write objects if they're queued for deletion.
     * @param o Object to add to the scene
     */
    public void add(GameObject o) {

        if (o == null || o.getTransform() == null) {
            LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i] == null) {
                sceneObjects[i] = o;
                LogExtra(String.format("Added '%s' (%s) to the scene Successfully", o.getIdentity().getName(), o.getClass().getSimpleName()));
                // sort by z to make sure the objects are in the correct order, not just how they're added to the array
                sortByZ();
                o.OnAdded();
                return;
            }
            else if(sceneObjects[i].isQueuedForDeletion() && !sceneHasRoom())
            {
                sceneObjects[i] = o;
                LogExtra(String.format("Overwrote '%s' (%s) queued for deletion. Added '%s' (%s) to the scene Successfully",sceneObjects[i].getIdentity().getName(), sceneObjects[i].getClass().getSimpleName(), o.getIdentity().getName(), o.getClass().getSimpleName()));
                // sort by z to make sure the objects are in the correct order, not just how they're added to the array
                sortByZ();
                return;
            }
        }
        // if scene is full, create a new array with 2 more spaces
        GameObject[] temp = new GameObject[sceneObjects.length + 20];
        for (int i = 0; i < sceneObjects.length; i++) {
            temp[i] = sceneObjects[i];
        }
        temp[temp.length - 1] = o;
        sceneObjects = temp;
        sortByZ();
        LogExtra(String.format("Added '%s' (%s) to the scene Successfully", o.getIdentity().getName(), o.getClass().getSimpleName()));
    }
    public void add(GameObject... objects){
        for (GameObject object :
                objects) {
            add(object);
        }
    }

    public void addUI(Node node){
        try {
            Platform.runLater(() -> {
                uiObjects.getChildren().add(node);
            });
        }
        catch (Exception e){
            LogWarning("UI already exists in scene or is Null!");
        }
    }

    public void addUI(Node... nodes){
        for (Node n :
                nodes) {
            addUI(n);
        }
    }

    /**
     * Remove a GameObject from the scene.
     * Note: Removing a GameObject from the scene just stops it from being rendered and any update functions being called
     * on it. It is not cleared from memory just yet...
     * It will be the first in line to be overwritten when there is no more space in the scene.
     *
     * Because of the soft way its being 'removed' from the scene, you are able to unDelete() an object if you have
     * its name and tag, or reference
     * @param o The GameObject to remove
     */
    public void remove(GameObject o)
    {
        if(o== null)
            return;
        o.OnDestroy();
        o.setQueuedForDeletion(true);
        for (Component c: o.getComponents()) {
            if(c == null)
                continue;
             c.setActive(false);
        }
        LogExtra(String.format("Queued object '%s' (%s) for deletion.", o.getIdentity().getName(), o.getClass().getSimpleName()));
    }
    public void removeUI(Node n)
    {
        if (n == null)
            return;
        Platform.runLater(() -> uiObjects.getChildren().remove(n));
    }


    /**
     * Attempt to restore an object Queued For Deletion
     * @param o Object to restore
     */
    public void unDelete(GameObject o)
    {
        for (GameObject ref :
                sceneObjects) {
            if(ref == o && ref.isQueuedForDeletion())
            {
                o.setQueuedForDeletion(false);
                for (Component c: o.getComponents()) {
                    if(c == null)
                        continue;
                    c.setActive(true);
                }
                LogExtra(String.format("Un-deleted '%s' (%s)", o.getIdentity().getName(), o.getClass().getSimpleName()));
                return;
            }
        }
        LogExtra(String.format("Could not Un-delete '%s' (%s)", o.getIdentity().getName(), o.getClass().getSimpleName()));
    }
    /**
     * Attempt to restore an object Queued For Deletion
     * @param identity Search scene for identity to restore
     */
    public void unDelete(Identity identity)
    {
        for (GameObject ref :
                sceneObjects) {
            if(ref.getIdentity().toString().equals(identity.getName() + " : " + identity.getTag()) && ref.isQueuedForDeletion())
            {
                ref.setQueuedForDeletion(false);
                LogExtra(String.format("Un-deleted '%s' (%s)", ref.getIdentity().getName(), ref.getClass().getSimpleName()));
                return;
            }
        }
        LogExtra(String.format("Could not Un-delete '%s'", identity.getName()));
    }

    public GameObject[] getObjects() {
        return sceneObjects;
    }

    /**
     * Set all the scene objects
     * @param sceneObjects Objects to set
     */
    public void setSceneObjects(GameObject[] sceneObjects) {
        this.sceneObjects = sceneObjects;
    }

    /**
     * Runs the Start() methods on all (active) objects in the scene
     */
    public void runStartBehaviors()
    {
        for (GameObject sceneObject : sceneObjects) {
            if (sceneObject != null) {
                if(sceneObject.getActive())
                {
                    sceneObject.Start();
                }
            }
        }
    }

    /* Because objects can have the same name and tag, we must return an array of objects in the event of duplicates
    * findObjectsByIdentity can get you the name of objects you only have the tag for or vice versa
     * it is not very efficient though, so it should NEVER be called in an update function. Call it once and create a ref
     * to the object instead.
    */

    /**
     * Find an object in the scene by Name, Tag, or Both
     * @param name The name of the object you would like to search for. Leave empty if not searching by name
     * @param tag The tag of the object you would like to search for. Leave empty if not searching by tag
     * @param searchType The type of search you would like to do. (SearchByName, SearchByTag, SearchByNameAndTag)
     * @return JObject reference from the scene
     */
    public GameObject[] findObjectsByIdentity(String name, String tag, SearchType searchType) {
        int count = 0;
        GameObject[] sceneSize = new GameObject[sceneObjects.length];

        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i] != null) {

                if (searchType == SearchType.SearchByName) {
                    if (sceneObjects[i].getIdentity().compareName(name)) {
                        sceneSize[i] = sceneObjects[i];
                        count++;
                    }
                } else if (searchType == SearchType.SearchByTag) {
                    if (sceneObjects[i].getIdentity().compareTag(tag)) {
                        sceneSize[i] = sceneObjects[i];
                        count++;
                    }
                } else {
                    if (sceneObjects[i].getIdentity().compareTag(tag) && sceneObjects[i].getIdentity().compareName(name)) {
                        sceneSize[i] = sceneObjects[i];
                        count++;
                    }
                }
            }
        }
        GameObject[] searchResult = new GameObject[count];
        int i = 0;
        for (GameObject o:
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
     * Find all objects of the same class
     */
    public GameObject[] findObjectsByClass(String className) {
        int count = 0;
        GameObject[] searchResult = new GameObject[1];

        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i] != null) {
                if (sceneObjects[i].getIdentity().getClass().getSimpleName().equals(className)) {
                    if(count>searchResult.length)
                    {
                        GameObject[] temp = new GameObject[searchResult.length*2];
                        System.arraycopy(searchResult, 0, temp, 0, searchResult.length);
                        searchResult = temp;
                    }
                    searchResult[count] = sceneObjects[i];
                    count++;
                }
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
    public void migrateScene(GameScene newScene, boolean force, boolean switchSceneUponCompletion)
    {
        if((newScene.sceneObjects.length < sceneObjects.length) && !force)
        {
            LogWarning("CANNOT MIGRATE TO NEW SCENE: NEW SCENE HAS LOWER MAX OBJECT COUNT AND WOULD DESTROY OBJECTS" +
                    " USE force=true TO BYPASS THIS WARNING");
            return;
        }
        for (int i = 0; i < newScene.sceneObjects.length; i++) {
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
            SceneManager.switchScene(newScene);
            LogInfo("Successfully Migrated scene content and activated scene.");
            return;
        }
        LogInfo("Successfully Migrated scene content. do camera.setActiveScene(newScene) to switch to new scene");
    }

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
     */
    public void purge()
    {
        sceneObjects = new GameObject[sceneObjects.length];
        uiObjects.getChildren().clear();
        LogInfo(String.format("Purged scene: '%s' of ALL contents.", getSceneName()));
    }

    public boolean contains(GameObject o)
    {
        for (GameObject g: sceneObjects) {
            if(g == o)
            {
                return true;
            }
        }
        return false;
    }

    public Group getUiObjects() {
        return uiObjects;
    }

    public void OnSceneActive(){}

    public void addLight(GameLight light)
    {
        lights.add(light);
        generateLightEffect();
    }
    public void generateLightEffect(){
        Effect allLights = lights.get(0).light;

        for (Object lighting : lights.toArray()) {
            allLights = new Blend(BlendMode.ADD, allLights, ((GameLight)lighting).light);
        }

        lightEffect = allLights;
    }
    public void removeLight(GameLight light)
    {
        lights.remove(light);
        generateLightEffect();
    }

    public Effect getLightEffect() {
        return lightEffect;
    }

    public void setEnableLighting(boolean enableLighting) {
        this.enableLighting = enableLighting;
    }

    public boolean getEnableLighting() {
        return enableLighting;
    }

    public void enableLighting()
    {
        enableLighting = true;
    }
    public void disableLighting()
    {
        enableLighting = false;
    }

    public CopyOnWriteArrayList getLights() {
        return lights;
    }

    public void setLights(CopyOnWriteArrayList lights) {
        this.lights = lights;
    }

    public void removeAmbientLighting(){
        lights.clear();
        lights.add(new GameLight(new Lighting(new Light.Distant(0,0,Color.TRANSPARENT)), false));
        generateLightEffect();
    }
}
