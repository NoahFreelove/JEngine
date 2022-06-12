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
    private boolean stopOnFirstTick;
    private boolean forceRunUpdate;
    /**
     * Create a timer that ticks every interval
     * @param interval interval for each tick in milliseconds
     * @param runEvents events to run each tick
     */
    public GameTimer(long interval, GenericMethod[] runEvents) {
        this.interval = interval;
        this.runEvents = runEvents;
    }
    public GameTimer(long interval, GenericMethod runEvents) {
        this.interval = interval;
        this.runEvents = new GenericMethod[]{runEvents};
    }
    public GameTimer(long interval, GenericMethod runEvent, boolean stopOnFirstTick) {
        this.interval = interval;
        this.runEvents = new GenericMethod[]{runEvent};
        this.stopOnFirstTick = stopOnFirstTick;
    }
    public GameTimer(long interval, GenericMethod runEvent, boolean stopOnFirstTick, boolean startImmediately) {
        this.interval = interval;
        this.runEvents = new GenericMethod[]{runEvent};
        this.stopOnFirstTick = stopOnFirstTick;
        if(startImmediately)
            start();
    }
    public GameTimer(long interval, GenericMethod runEvent, boolean stopOnFirstTick, boolean startImmediately,boolean forceRunUpdate) {
        this.interval = interval;
        this.runEvents = new GenericMethod[]{runEvent};
        this.stopOnFirstTick = stopOnFirstTick;
        this.forceRunUpdate = forceRunUpdate;
        if(startImmediately)
            start();
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

    public void setRunEvents(GenericMethod[] runEvents) {
        this.runEvents = runEvents;
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
        if(!SceneManager.getWindow().isPaused() || forceRunUpdate)
        {
            runBehaviors();
        }
        if(stopOnFirstTick)
        {
            stop();
        }
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
