package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.PrimitiveTypes.Behavior;

import javax.swing.*;

/** JEngine.JWindow (c) Noah Freelove
 * Brief Explanation:
 * JWindow is a way to create a window and have it display camera content.
 *
 * Usage:
 * JWindow provides an update function and a way to set FPS
 * **/

public class JWindow extends Thing {

    private JFrame frame;
    public JCamera activeCamera;
    public boolean isActive;
    public int totalFrames;

    private float targetFPS = 5;

    JPanel panel;
    Behavior[] behaviors;

    public JFrame getWindow() {return frame;}
    public void setWindow(JFrame newFrame) {frame = newFrame;}
    public void setVisibility(boolean newVisibility) {frame.setVisible(newVisibility);}

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
        isActive = defaultVisibilityState;
        behaviors = new Behavior[maxBehaviors];
    }

    public void AddUpdateBehavior(Behavior newBehavior)
    {
        for (int i = 0; i < behaviors.length; i++) {
            if(behaviors[i] == null)
            {
                behaviors[i] = newBehavior;
                break;
            }
        }
    }

    public void refreshWindow(JPanel newPanel)
    {
        newPanel.setLayout(null);
        panel = newPanel;
        panel.repaint();
    }

    public void setTargetFPS(float newTargetFPS) { targetFPS = newTargetFPS; }


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
        for (Behavior behavior : behaviors) {
            if (behavior != null) {
                behavior.behave();
            }
        }
    }
}
