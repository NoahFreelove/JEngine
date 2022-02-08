package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.PrimitiveTypes.Worker;

import javax.swing.*;

public class JWindow extends Thing {
    public JFrame frame;
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
        frame.repaint();
    }

    public void setDesiredFPS(float newDesiredFPS) {desiredFPS = newDesiredFPS;}

    public void start() throws InterruptedException {
        run();
    }

    double interpolation = 0;
    final int SKIP_TICKS = (int) (1000 / desiredFPS);
    final int MAX_FRAMESKIP = 5;

    public void run() {
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (isActive) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick
                    && loops < MAX_FRAMESKIP) {
                totalFrames++;
                update(totalFrames);

                next_game_tick += SKIP_TICKS;
                loops++;
            }

            interpolation = (System.currentTimeMillis() + SKIP_TICKS - next_game_tick
                    / (double) SKIP_TICKS);
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
        for (Worker worker : workers) {
            if (worker != null) {
                worker.work();
            }
        }
    }
}
