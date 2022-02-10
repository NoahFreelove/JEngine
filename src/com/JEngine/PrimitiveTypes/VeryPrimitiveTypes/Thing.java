package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

import com.JEngine.Main;

/** JEngine.Thing (c) Noah Freelove
 * Brief Explanation:
 * A Thing is the most basic type in JEngine. It does not require a transform, just an active state.
 * Everything is built off of a Thing so every JObject has an active state and can log
 *
 * Inactive objects can still be affected, but they will never be rendered.
 * **/

public class Thing {
    boolean isActive;

    public Thing(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getActive() {return isActive;}
    public void setActive(boolean newActiveState) {isActive = newActiveState;}

    public void LogInfo(String log)
    {
        if(Main.savedArgs.length>=1) {
            if (Main.savedArgs[0].equals("logs")) {
                System.out.println("Info: " + log);
            }
        }
    }

    public void LogWarning(String log)
    {
        if(Main.savedArgs.length>=1) {
            if (Main.savedArgs[0].equals("logs")) {
                System.out.println("WARNING: " + log);
            }
        }
    }

    public void LogError(String log)
    {
        if(Main.savedArgs.length>=1) {
            if (Main.savedArgs[0].equals("logs")) {
                System.out.println("CAUGHT ERROR: " + log);
            }
        }
    }

    public void LogExtra(String log)
    {
        if(Main.savedArgs.length>=2) {
            if (Main.savedArgs[0].equals("logs") && Main.savedArgs[1].equals("extra")) {
                System.out.println("Extra: " + log);
            }
        }
    }
}
