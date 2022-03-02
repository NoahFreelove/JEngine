package com.JEngine.Utility.Misc;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogWarning;

public class JTimer {
    public float interval;
    public boolean isRunning;
    public GenericMethodCall[] runEvents;
    private Thread t;

    /**
     * Create a timer that ticks every interval
     * @param interval interval for each tick in milliseconds
     * @param runEvents events to run each tick
     */
    public JTimer(float interval, GenericMethodCall[] runEvents) {
        this.interval = interval;

        this.runEvents = runEvents;

    }
    private void tick() throws InterruptedException {
        Thread.sleep((long) interval);
        runBehaviors();
    }

    private void runBehaviors() throws InterruptedException {
        for (GenericMethodCall method :
                runEvents) {
         method.call(null);
        }
        tick();
    }

    public void start()
    {
        if(isRunning)
        {
            LogWarning("Timer already running!");
            return;
        }
        t = new Thread(() -> {
            try {
                tick();
            } catch (InterruptedException e) {
                //ignore
            }
        });
        t.start();
        isRunning = true;
    }

    public void stop()
    {
        t.interrupt();
        isRunning = false;
    }
}
