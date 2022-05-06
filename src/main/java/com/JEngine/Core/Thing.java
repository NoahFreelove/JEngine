package com.JEngine.Core;

import com.JEngine.Utility.Settings.EnginePrefs;

import java.io.Serializable;

/** Thing (c) Noah Freelove
 * Brief Explanation:
 * A Thing is the most basic type in com.JEngine. It does not require a transform, just an active state.
 * Things are useless on their own but can be used to create more complex objects.
 * **/

public class Thing implements Serializable {
    boolean isActive;

    public Thing(boolean isActive) {
        this.isActive = isActive;
    }

    // Get and Set Active state
    public boolean getActive() {return isActive;}
    public void setActive(boolean newActiveState) {isActive = newActiveState;}

    public static void LogInfo(String log)
    {
        if (EnginePrefs.logInfo) {
            System.out.println("Info: " + log);
        }
    }

    public static void LogWarning(String log)
    {
        if (EnginePrefs.logInfo) {
            System.out.println("WARNING: " + log);
        }
    }

    public static void LogError(String log)
    {
        if (EnginePrefs.logInfo) {
            System.out.println("CAUGHT ERROR: " + log);
        }
    }

    public static void LogExtra(String log)
    {
        if (EnginePrefs.logExtra) {
            System.out.println("Extra: " + log);
        }
    }

    public static void LogDebug(String log)
    {
        if (EnginePrefs.logDebug) {
            System.out.println("Debug: " + log);
        }
    }

    public static void LogImportant(String log)
    {
        if (EnginePrefs.logImportant) {
            System.out.println("\n\n----------------------------\nIMPORTANT: " + log + "\n----------------------------\n\n");
        }
    }
}
