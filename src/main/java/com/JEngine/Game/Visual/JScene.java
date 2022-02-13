package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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

    public void add(JObject o) {
        if (o == null || o.transform == null) {
            super.LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < sceneObjects.length; i++) {
            if (sceneObjects[i] == null) {
                sceneObjects[i] = new ObjRef(o);
                sceneObjects[i].objRef.Start();
                super.LogInfo("Added object to scene " + ((sceneObjects[i] != null) ? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        super.LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");
    }

    public void addUI(JUIObject o) {
        if (o == null || o.transform == null) {
            super.LogWarning("Tried to add null object or transform to scene!");
            return;
        }
        for (int i = 0; i < juiObjects.length; i++) {
            if (juiObjects[i] == null) {
                juiObjects[i] = o;
                juiObjects[i].Start();
                super.LogInfo("Added UI element to scene " + ((juiObjects[i] != null) ? "successfully" : "UNSUCCESSFULLY"));
                return;
            }
        }
        super.LogError("Could not add object to full scene! Try increasing the maxObjects parameter.");

    }

    private JObject convertTextToObject(String className, String name, String tag, boolean isActive, String filePath, int sizeX, int sizeY, Transform transform)
    {
        if(className.equals("JPawn"))
        {
            return new JPawn(transform, new JImage(isActive, filePath, sizeX, sizeY), new JIdentity(name,tag));
        }

        return null;
    }

    public void loadFromFile(String filepath)
    {
        try {
            File fp = new File(filepath);
            FileReader fr = new FileReader(fp);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            boolean inObject = false;

            String className = "";
            String name = "";
            String tag = "";
            boolean isActive = false;
            String imagePath= "";
            int sizeX = 0;
            int sizeY = 0;

            int posX = 0;
            int posY = 0;
            int posZ = 0;
            int rotX = 0;
            int rotY = 0;
            int rotZ = 0;
            int scaleX = 0;
            int scaleY = 0;
            int scaleZ = 0;

            while((line = br.readLine()) != null) {
                LogAnnoyance("SCENE LOAD FROM FILE: " + line);
                if(line.equals("startObject"))
                {
                    inObject = true;
                    continue;
                }
                if(line.equals("endObject"))
                {
                    inObject = false;
                    add(convertTextToObject(className, name, tag, isActive, imagePath, sizeX, sizeY, new Transform(new Vector3(posX,posY,posZ), new Vector3(rotX,rotY,rotZ), new Vector3(scaleX, scaleY, scaleZ))));
                    continue;
                }
                if(inObject)
                {
                    if(line.contains("class="))
                    {
                        className = line.replace("class=","");
                    }
                    else if(line.contains("Name="))
                    {
                        name = line.replace("Name=","");
                    }
                    else if(line.contains("Tag="))
                    {
                        tag = line.replace("Tag=","");
                    }
                    else if(line.contains("IsActive="))
                    {
                        tag = line.replace("Tag=","");
                    }
                    else if(line.contains("ImagePath="))
                    {
                        imagePath = line.replace("ImagePath=","");
                    }
                    else if(line.contains("SizeX="))
                    {
                        sizeX = Integer.parseInt(line.replace("SizeX=",""));
                    }
                    else if(line.contains("SizeY="))
                    {
                        sizeY = Integer.parseInt(line.replace("SizeY=",""));
                    }
                    else if(line.contains("PosX="))
                    {
                        posX = Integer.parseInt(line.replace("PosX=",""));
                    }
                    else if(line.contains("PosY="))
                    {
                        posY = Integer.parseInt(line.replace("PosY=",""));
                    }
                    else if(line.contains("PosZ="))
                    {
                        posZ = Integer.parseInt(line.replace("PosZ=",""));
                    }
                    else if(line.contains("RotX="))
                    {
                        rotX = Integer.parseInt(line.replace("RotX=",""));
                    }
                    else if(line.contains("RotY="))
                    {
                        rotY = Integer.parseInt(line.replace("RotY=",""));
                    }
                    else if(line.contains("RotZ="))
                    {
                        rotZ = Integer.parseInt(line.replace("RotZ=",""));
                    }
                    else if(line.contains("ScaleX="))
                    {
                        scaleX = Integer.parseInt(line.replace("ScaleX=",""));
                    }
                    else if(line.contains("ScaleY="))
                    {
                        scaleY = Integer.parseInt(line.replace("ScaleY=",""));
                    }
                    else if(line.contains("ScaleZ="))
                    {
                        scaleZ = Integer.parseInt(line.replace("ScaleZ=",""));
                    }
                }
            }
            fr.close();
            LogInfo(String.format("Successfully loaded scene from file: %s", filepath));
        }
        catch (Exception e)
        {
            LogError("Error when loading scene from file. Enable (log) extra for details:");
            LogExtra(e.toString());
        }
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
