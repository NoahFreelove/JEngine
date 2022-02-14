package com.JEngine.Utility;

import javafx.stage.WindowEvent;

public class JUtility {

    public static void waitForSeconds(long seconds, WaitForSecondsEvent event) {
        new Thread(() -> {
            try {
                long milliseconds = seconds * 1000;
                Thread.sleep(milliseconds);
                event.finishedWaiting();
            } catch (InterruptedException e) {
                System.out.println(e);
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
