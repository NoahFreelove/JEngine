package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.GameObject;
import com.JEngine.Utility.IO.FileOperations;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Abandoned this class for now....
 * Until I find a better way to load class values. likely json related
 */
public class SceneLoader {
    public static GameScene load(File filepath, String sceneName)
    {
        GameScene scene = new GameScene(500, sceneName);

        String[] lines = FileOperations.fileToStringArr(filepath.getAbsolutePath());
        Class currObjectClass = null;
        Object deserializedObject = null;
        for (String line : lines) {
            if(line == null)
                continue;
            if (line.startsWith("class:")) {
                String className = line.substring(6);
                try {
                    currObjectClass = Class.forName(className);
                }
                catch (ClassNotFoundException e)
                {
                    System.out.println("Class not found: " + className);
                }
            }
            else {
                try
                {
                    deserializedObject = currObjectClass.cast(deserialize(line.getBytes(StandardCharsets.UTF_8)));

                    scene.add((GameObject) deserializedObject);

                }catch (Exception e)
                {
                    System.out.println("Error Adding or Deserializing object: " + e.getMessage());
                }
            }
        }

        return scene;
    }

    public static void save(GameScene scene, String optionalName){
        String[] lines = new String[scene.getObjects().length*3];
        int i = 0;
        for (GameObject obj: scene.getObjects()) {
            if(obj == null)
                continue;
            lines[i] = "class:" + obj.getClass().getName();
            lines[i+1] = new String(serialize(obj));
            i+=2;
        }

        FileOperations.stringArrToFile(lines, new File("SavedScenes/" + scene.getSceneName()+optionalName + ".scene").getAbsolutePath());
    }

    public static Object deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInput in = new ObjectInputStream(bis);
            return in.readObject();
        } catch (Exception e)
        {
            System.out.println("Error deserializing object: " + e.getMessage());
        }
        return new Object();
    }

    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            System.out.println("Error serializing object: " + e.getMessage());
        }
        return new byte[0];
    }
}
