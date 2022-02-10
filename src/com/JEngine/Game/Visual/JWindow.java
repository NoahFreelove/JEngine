package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.JIcon;
import com.JEngine.PrimitiveTypes.ObjRef;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

import javax.swing.*;

/** JEngine.JWindow (c) Noah Freelove
 * Brief Explanation:
 * JWindow is a way to create a window and have it display camera content.
 *
 * Usage:
 * JWindow provides an update function and a way to set FPS
 * **/

public class JWindow extends Thing {

    public JFrame frame;
    public JCamera activeCamera;
    public boolean isActive;
    public int totalFrames;
    private Thread updateThread;
    private float targetFPS = 5;

    JPanel panel;

    public JFrame getWindow() {
        return frame;
    }

    public void setWindow(JFrame newFrame) {
        frame = newFrame;
    }

    public void setVisibility(boolean newVisibility) {
        frame.setVisible(newVisibility);
    }

    public void setIcon(JIcon newIcon) {
        if (newIcon.getIcon() != null)
        {
            frame.setIconImage(newIcon.getIcon().getImage());
            return;
        }
        LogWarning("Tried to set window icon to a null image");

    }

    public JWindow(int sizeX, int sizeY, String title, boolean defaultVisibilityState, int maxBehaviors) {
        super(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.setResizable(false);

        panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        frame.setSize(sizeX, sizeY);
        frame.setVisible(defaultVisibilityState);
    }

/*    public void AddUpdateBehavior(Behavior newBehavior) {
        for (int i = 0; i < behaviors.length; i++) {
            if (behaviors[i] == null) {
                behaviors[i] = newBehavior;
                break;
            }
        }
    }*/

    public void refreshWindow(JPanel newPanel) {
        //newPanel.setLayout(null);
        panel = newPanel;
        panel.revalidate();

        panel.repaint();
        frame.repaint();
    }

    public void setTargetFPS(float newTargetFPS) {
        targetFPS = newTargetFPS;
    }

    public void start()
    {
        if(isActive)
        {
            LogError("Window is already active! Cannot start another update thread.");
            return;
        }
        isActive = true;
        updateThread = new Thread(this::refresh);
        updateThread.start();
        LogInfo("Successfully started window");

    }

    public void stop()
    {
        try
        {
            updateThread.interrupt();
            isActive = false;
            LogInfo("Successfully stopped window");
        }
        catch (Exception ignored)
        {
            LogError("Could not stop window thread");
        }
    }

    // Refresh rate logic
    public void refresh() {
        final int maxFrameSkip = 5;
        int SKIP_TICKS = (int) (1000 / targetFPS);
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (isActive) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick
                    && loops < maxFrameSkip) {
                totalFrames++;
                update(totalFrames);
                next_game_tick += SKIP_TICKS;
                loops++;

            }
        }
    }


    public void setCamera(JCamera camera)
    {
        this.activeCamera = camera;
        LogInfo("Set Camera");
    }

    void update(int frameNumber) {
        LogExtra(String.format("New frame (#%d)", frameNumber));

        if(activeCamera !=null)
        {
            activeCamera.InitiateRender();
        }

        runUpdateBehaviors();

    }

    private void runUpdateBehaviors() {
        for (ObjRef objRef : activeCamera.getActiveScene().sceneObjects) {
            if(objRef == null)
                return;
            objRef.objRef.Update();
        }
    }
}
