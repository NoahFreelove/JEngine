package com.JEngine.Utility;

import javafx.stage.WindowEvent;

public class JUtility {

    public static void waitForSeconds(long seconds, WaitForSecondsEvent event) {
        new Thread(() -> {
            try {
                Thread.sleep((seconds*1000));
                event.finishedWaiting();
            } catch (InterruptedException e) {
                //Ignore
            }
        }).start();
    }

    public static void exit(){
        System.exit(0);
    }
    public static void exitHandler(WindowEvent w)
    {
        exit();
    }
}
