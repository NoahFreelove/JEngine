package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

import com.JEngine.Main;

/** JEngine.Thing (c) Noah Freelove
 * Brief Explanation:
 * A Thing is the most basic type in JEngine. It does not require a transform, just an active state.
 * **/

public class Thing {
    boolean isActive;

    public Thing(boolean isActive) {
        this.isActive = isActive;
    }

    public void LogInfo(String log)
    {
        if(Main.savedArgs.length>=1) {
            if (Main.savedArgs[0].equals("logs")) {
                System.out.println("INFO: " + log);
            }
        }
    }

    public void LogWarning(String log)
    {
        if(Main.savedArgs.length>=1) {
            if (Main.savedArgs[0].equals("logs")) {
                System.out.println("INFO: " + log);
            }
        }
    }

    public void LogError(String log)
    {
        if(Main.savedArgs.length>=1) {
            if (Main.savedArgs[0].equals("logs")) {
                System.out.println("INFO: " + log);
            }
        }
    }
}
