package com.JEngine.Utility.Misc;

import com.JEngine.Core.Thing;
import javafx.stage.WindowEvent;

/** JUtility (c) Noah Freelove
 * A collection of useful methods.
 */
public class GameUtility {

    /**
     * Wait for seconds then run method
     * @param seconds The amount of seconds to wait
     * @param event The event to run
     * @param args The arguments to pass to the event
     */
    public static void waitForSeconds(double seconds, GenericMethod event, Object[] args) {
        new Thread(() -> {
            try {
                long milliseconds = (long)(seconds * 1000);
                Thread.sleep(milliseconds);
                event.call(args);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }).start();
    }
    public static void waitForSeconds(double seconds, GenericMethod event) {
        new Thread(() -> {
            try {
                long milliseconds = (long)(seconds * 1000);
                Thread.sleep(milliseconds);
                event.call(null);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }).start();
    }
    // Exit the program (background process still runs)
    static void systemExit() {
        System.exit(0);
    }

    static void gc(){
        long beforeUsedMem=Runtime.getRuntime().totalMemory();
        System.gc();
        long afterUsedMem=Runtime.getRuntime().totalMemory();
        long savedMemory = beforeUsedMem-afterUsedMem;
        Thing.LogExtra("Freed " + savedMemory/1024/1024 + " MB");
    }

    // Close the window
    public static void exitWindow(WindowEvent ignoredWindowEvent)
    {
        systemExit();
    }

    // Close the window (without args)
    public static void exitApp()
    {
        exitWindow(null);
    }

}
