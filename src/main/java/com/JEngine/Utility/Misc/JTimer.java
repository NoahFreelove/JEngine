package com.JEngine.Utility.Misc;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogWarning;

public class JTimer {
    public long interval;
    private long intervalRemaining;
    public boolean isRunning;
    public GenericMethodCall[] runEvents;

    /**
     * Create a timer that ticks every interval
     * @param interval interval for each tick in milliseconds
     * @param runEvents events to run each tick
     */
    public JTimer(long interval, GenericMethodCall[] runEvents) {
        this.interval = interval;

        this.runEvents = runEvents;

    }
    public long getIntervalRemaining()
    {
        return intervalRemaining;
    }

    private void tick() throws InterruptedException {

        for (int i = 0; i < interval; i+=2) {
            intervalRemaining = interval-i;
            Thread.sleep(1);
            if(!isRunning){
                return;
            }
        }
        runBehaviors();

        if(isRunning)
        {
            tick();
        }

    }

    private void runBehaviors() throws InterruptedException {
        for (GenericMethodCall method :
                runEvents) {
         method.call(null);
        }
    }

    public void start()
    {
        if(isRunning)
        {
            LogWarning("Timer already running!");
            return;
        }

        Thread t = new Thread(() -> {
            isRunning = true;
            try {
                tick();
            } catch (InterruptedException e) {
                //ignore
            }
        });
        t.start();
    }

    public void stop()
    {
        isRunning = false;
    }
}
