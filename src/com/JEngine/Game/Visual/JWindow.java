package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.PrimitiveTypes.Worker;

import javax.swing.*;
import java.sql.Time;

public class JWindow extends Thing {
    private JFrame frame;
    public JCamera activeCamera;
    public boolean isActive;
    private float desiredFPS = 5;
    JPanel panel;
    public int totalFrames;
    Worker[] workers = new Worker[10];

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
        isActive = defaultVisibilityState;
    }
    public void AddUpdateBehavior(Worker newWorker)
    {
        for (int i = 0; i < workers.length; i++) {
            if(workers[i] == null)
            {
                workers[i] = newWorker;
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

    public void setDesiredFPS(float newDesiredFPS) {desiredFPS = newDesiredFPS;}

    public void start() {
        run();
    }

    // Refresh rate logic
    public void run() {
        final int maxFrameSkip = 5;
        int SKIP_TICKS = (int) (1000 / desiredFPS);
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

    void update(int frameNumber)
    {
        LogExtra(String.format("New frame (#%d)", frameNumber));

        if(activeCamera !=null)
        {
            activeCamera.InitiateRender();
        }

        runUpdateBehaviors();

    }

    private void runUpdateBehaviors()
    {
        for (Worker worker : workers) {
            if (worker != null) {
                worker.work();
            }
        }
    }
}
