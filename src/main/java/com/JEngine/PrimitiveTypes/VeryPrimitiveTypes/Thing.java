package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

import com.JEngine.Utility.Settings.EnginePrefs;

/** com.JEngine.Thing (c) Noah Freelove
 * Brief Explanation:
 * A Thing is the most basic type in com.JEngine. It does not require a transform, just an active state.
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

    public static void LogAnnoyance(String log)
    {
        if (EnginePrefs.logAnnoyance) {
            System.out.println("Annoyance: " + log);
        }
    }

    public static void LogImportant(String log)
    {
        if (EnginePrefs.logImportant) {
            System.out.println("\n\n----------------------------\nIMPORTANT: " + log + "\n----------------------------\n\n");
        }
    }
}
