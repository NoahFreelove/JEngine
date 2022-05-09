package com.JEngine.Utility.Settings;

/** EnginePrefs (c) Noah Freelove
 * Set the engine logging preferences.
 * There might be more added later.
 */
public class EnginePrefs {
    //region logging
    public static boolean logInfo;
    public static boolean logExtra;
    public static boolean logDebug;
    public static boolean logImportant;
    //endregion

    //region garbage collection
    // will force gc every frame. Will have noticeable impact on frame rate
    public static boolean aggressiveGC;
    //endregion
}
