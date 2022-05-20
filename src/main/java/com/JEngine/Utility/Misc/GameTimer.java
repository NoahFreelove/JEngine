package com.JEngine.Utility.Misc;

import com.JEngine.Core.Thing;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;

/** GameTimer (c) Noah Freelove
 * GameTimer is a very useful class for running methods every interval
 */

public class GameTimer {
    private long interval;
    private long intervalRemaining;
    private boolean isRunning;
    private GenericMethod[] runEvents;

    /**
     * Create a timer that ticks every interval
     * @param interval interval for each tick in milliseconds
     * @param runEvents events to run each tick
     */
    public GameTimer(long interval, GenericMethod[] runEvents) {
        this.interval = interval;
        this.runEvents = runEvents;
    }
    // check if timer is running
    public boolean isRunning(){return isRunning;}
    // get interval remaining
    public long getIntervalRemaining()
    {
        return intervalRemaining;
    }

    // Interval getter & setter
    public long getInterval() {
        return interval;
    }
    public void setInterval(long interval) {
        this.interval = interval;
    }

    // Ticks down and until 0, then runs the behaviors
    private void tick() throws InterruptedException {

        for (int i = 0; i < interval; i += 2) {
            intervalRemaining = interval - i;
            Thread.sleep(1);
            if (!isRunning) {
                return;
            }
        }
        runBehaviors();
        if(isRunning)
        {
            tick();
        }

    }

    /**
     * Runs the interval behaviors
     */
    private void runBehaviors() {
        for (GenericMethod method :
                runEvents) {
         method.call(null);
        }
    }

    /**
     * Starts the timer
     */
    public void start()
    {
        if(isRunning)
        {
            Thing.LogWarning("Timer already running!");
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

    /**
     * Stops the timer
     */
    public void stop()
    {
        isRunning = false;
    }
}
