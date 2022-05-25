package com.JEngine.Game.Visual.Scenes;

import com.JEngine.Components.DontDestroyOnLoad_Comp;
import com.JEngine.Core.Component;
import com.JEngine.Core.GameObject;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import javafx.application.Platform;

/** SceneManager (c) Noah Freelove
 * Brief Explanation:
 * SceneManager is a class that manages the current scene, window, and main camera.
 * Lets you reference the current scene, window, and camera from anywhere.
 */

public class SceneManager {
    private static GameScene activeScene;
    private static GameWindow window;
    private static GameCamera activeCamera;
    private static boolean hasInit;
    /**
     * Get the active scene
     * @return active scene
     */
    public static GameScene getActiveScene() {
        return activeScene;
    }

    /**
     * Set the initial values
     * @param newScene initial scene
     * @param newWindow initial window
     * @param newMainCamera initial main camera
     */
    public static void init(GameScene newScene, GameWindow newWindow, GameCamera newMainCamera)
    {
        if(hasInit)
            return;
        hasInit = true;
        SceneManager.activeScene = newScene;
        newScene.OnSceneActive();
        SceneManager.window = newWindow;
        SceneManager.activeCamera = newMainCamera;
        SceneManager.activeCamera.setActive(true);
        SceneManager.window.start();
    }

    /**
     * Set the active scene
     * @param newScene new active scene
     */
    public static void switchScene(GameScene newScene) {

        doSceneSwitch(newScene, false);

    }

    public static void switchScene(GameScene newScene,boolean ignoreDontDestroyOnLoad) {

        doSceneSwitch(newScene, ignoreDontDestroyOnLoad);

    }
    private static void doSceneSwitch(GameScene newScene, boolean ignoreDontDestroyOnLoad) {

        Platform.runLater(() -> {
            if (!ignoreDontDestroyOnLoad) {
                addDontDestroys(newScene);
            }
            if (activeCamera != null) {
                activeCamera.setActiveScene(newScene);
            }

            try {
                window.parent.getChildren().remove(activeScene.uiObjects);
                SceneManager.activeScene = newScene;
                newScene.OnSceneActive();
                window.parent.getChildren().add(newScene.uiObjects);
            } catch (Exception e)
            {
                //Ignore
            }
        } );

    }

    /**
     * Set the active window
     * @param newWindow new active window
     */
    public static void setWindow(GameWindow newWindow)
    {
        newWindow.setScene(window.getScene());
        SceneManager.window = newWindow;
    }

    /**
     * Set the active camera
     * @param newMainCamera new active camera
     */
    public static void setActiveCamera(GameCamera newMainCamera)
    {
        SceneManager.activeCamera = newMainCamera;
    }

    /**
     * Get the active window
     * @return active window
     */
    public static GameWindow getWindow() {
        return window;
    }

    /**
     * Get the active camera
     * @return the active camera
     */
    public static GameCamera getActiveCamera() {
        return activeCamera;
    }

    private static void addDontDestroys(GameScene newScene){
        for (GameObject o : activeScene.getObjects()
             ) {
            if(o==null)
                continue;
            o.OnUnload();
            Component[] c = o.getComponents();
            for (Component c1 : c) {
                if(c1 != null)
                {
                    if(c1.getActive() && c1 instanceof DontDestroyOnLoad_Comp)
                    {
                        if(!newScene.contains(o))
                        {
                            newScene.add(o);
                        }
                    }
                }
            }
        }
    }
}
