package com.JEngine.Utility.Misc;

import javafx.stage.WindowEvent;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JUtility {

    public static void waitForSeconds(long seconds, GenericMethodCall event, Object[] args) {
        new Thread(() -> {
            try {
                long milliseconds = seconds * 1000;
                Thread.sleep(milliseconds);
                event.call(args);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }).start();
    }
    public static String[] readFile(String filepath) {
        try {
            return  Files.readAllLines(Paths.get(filepath)).toArray(new String[0]);
        }
        catch(Exception e){
            return null;
        }
    }

    static void systemExit() {
        System.exit(0);
    }

    public static void exitWindow(WindowEvent w)
    {
        systemExit();
    }

    public static void exitApp()
    {
        exitWindow(null);
    }

}
