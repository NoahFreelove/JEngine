package com.JEngine.Utility.Settings;

/** EnginePrefs (c) Noah Freelove
 * Set the engine logging preferences.
 * There might be more added later.
 */
public class EnginePrefs {
    //region logging
    public static boolean logInfo;
    public static boolean logExtra;
    public static boolean logAnnoyance;
    public static boolean logImportant;
    //endregion

    //region garbage collection
    // will force gc every frame. Will have noticeable impact on memory usage
    // Cpu effects not measured but could be high with larger scenes.
    public static boolean aggressiveGC;
    //endregion
}
