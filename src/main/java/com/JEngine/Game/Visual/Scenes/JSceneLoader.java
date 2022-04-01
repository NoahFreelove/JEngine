package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.PlayersAndPawns.JPawn;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import com.JEngine.Utility.Settings.EnginePrefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.*;

/**
 * Abandoned this class for now....
 * Until I find a better way to load class values. likely json related
 */
public class JSceneLoader {
    public static JScene load(String filepath, JWindow window, String sceneName)
    {

        JScene scene = null;
        try {
            File fp = new File(filepath);
            FileReader fr = new FileReader(fp);
            BufferedReader br = new BufferedReader(fr);

            String line;
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
            boolean doOnce = false;

            while((line = br.readLine()) != null) {
                LogAnnoyance("SCENE LOAD FROM FILE: " + line);
                if(!doOnce)
                {
                    doOnce = true;
                    int maxObj = Integer.parseInt(line.replace("maxObjects=",""));
                    scene = new JScene(maxObj, sceneName);
                }
                if(line.equals("startObject"))
                {
                    inObject = true;
                    continue;
                }
                if(line.equals("endObject"))
                {
                    inObject = false;
                    scene.add(convertTextToObject(className, name, tag, isActive, imagePath, sizeX, sizeY, new Transform(new Vector3(posX,posY,posZ), new Vector3(rotX,rotY,rotZ), new Vector3(scaleX, scaleY, scaleZ))));
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
            if(!EnginePrefs.logExtra)
            {
                LogError("Error when loading scene from file. EnginePrefs.logExtra = true; for more details:");
                return scene;
            }

            LogExtra("Error when loading scene from file: \n" + e);
        }
        return scene;
    }

    /**
     * converts literal strings to a JObject
     * @param className The object's classname
     * @param name The object's JIdentity name
     * @param tag The object's JIdentity tag
     * @param isActive Is the object active by default?
     * @param filePath The object's image filepath (if applicable)
     * @param sizeX The object's image xSize (if applicable)
     * @param sizeY The object's image ySize (if applicable)
     * @param transform The object's transform (if applicable)
     * @return JObject with the inputted parameters
     */
    private static JObject convertTextToObject(String className, String name, String tag, boolean isActive, String filePath, int sizeX, int sizeY, Transform transform)
    {
        if(className.equals("JPawn"))
        {
            return new JPawn(transform, new JImage(isActive, filePath, sizeX, sizeY), new JIdentity(name,tag));
        }

        return null;
    }
}
