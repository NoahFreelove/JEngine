package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

import javax.swing.*;

/** JEngine.JWindow (c) Noah Freelove
 * Brief Explanation:
 * A JWindow is an extension from javax.swing
 *
 * Usage:
 * A JWindow can be used to create a window. A window is needed to create a scene.
 * **/

public class JWindow extends Thing {
    public JFrame frame;
    public JCamera activeCamera;
    private float desiredFPS = 5;
    JPanel panel;

    public int totalFrames;
    public JFrame getWindow() {return frame;}
    public void setWindow(JFrame newFrame) {frame = newFrame;}
    public void setVisibility(boolean newVisibility) {frame.setVisible(newVisibility);}

    public JWindow(int sizeX, int sizeY, String title, boolean defaultVisibilityState) throws InterruptedException {
        super(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);

        panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        frame.setSize(sizeX, sizeY);
        frame.setVisible(defaultVisibilityState);
    }

    public void refreshWindow(JPanel newPanel)
    {
        newPanel.setLayout(null);
        panel = newPanel;
        System.out.println("Refreshed");
        frame.repaint();
    }

    public void setDesiredFPS(float newDesiredFPS) {desiredFPS = newDesiredFPS;}

    public void start() throws InterruptedException {
        frameUpdate();
    }

    void frameUpdate() throws InterruptedException {
        long fps = (long) (1000/desiredFPS);
        while (true)
        {
            Thread.sleep(fps);
            totalFrames++;
            update(totalFrames);
        }
    }
    public void setCamera(JCamera camera)
    {
        this.activeCamera = camera;
    }

    void update(int frameNumber)
    {
        LogInfo(String.format("New frame (#%d)", frameNumber));

        if(activeCamera !=null)
        {
            activeCamera.InitiateRender();
        }
    }
}
