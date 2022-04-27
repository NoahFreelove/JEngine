package com.JEngine.Utility.Misc;

import javafx.stage.WindowEvent;

/** JUtility (c) Noah Freelove
 * A collection of useful methods.
 */
public class JUtility {

    /**
     * Wait for seconds then run method
     * @param seconds The amount of seconds to wait
     * @param event The event to run
     * @param args The arguments to pass to the event
     */
    public static void waitForSeconds(double seconds, GenericMethodCall event, Object[] args) {
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
    // Exit the program (background process still runs)
    static void systemExit() {
        System.exit(0);
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
