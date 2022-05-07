package com.JEngine.Utility.About;

import com.JEngine.Core.Thing;

/** GameInfo (c) Noah Freelove
 * Easily store and get your application's information.
 */

public class GameInfo {
    public static String appName;
    public static String[] authors;
    public static int year;
    public static int appVersionMajor;
    public static float appVersionMinor;
    public static boolean isCopyright;
    public static String buildID;
    public static String changeLog;
    /**
     * Get the app info neatly formatted in a string.
     * @return The app's info
     */
    public static String getAppInfo()
    {
        if(authors == null)
            return "JAppInfo Author is Null!";
        boolean multipleAuthors = !(authors.length == 1);
        StringBuilder concatAuthors = new StringBuilder();
        int i = 1;
        for (String a :
                authors) {
            if(i == authors.length)
            {
                concatAuthors.append(a);
                break;
            }
            concatAuthors.append(a).append(" & ");
            i++;
        }
        if(isCopyright)
        {
            if (!multipleAuthors)
            {
                return String.format("%s : Created by %s : Copyright %d : Version %d.%.2f (%s)", appName, authors[0], year, appVersionMajor, appVersionMinor, buildID);
            }

            return String.format("%s : Copyright %d : Version %d.%.2f (%s)\nCreated by: %s", appName, year, appVersionMajor, appVersionMinor, buildID, concatAuthors);
        }
        else {
            if (!multipleAuthors)
            {
                return String.format("%s : Created by %s : %d : Version %d.%.2f (%s)", appName, authors[0], year, appVersionMajor, appVersionMinor, buildID);

            }
            return String.format("%s : Created by %s : %d : Version %d.%.2f (%s)\nCreated by: %s", appName, authors[0], year, appVersionMajor, appVersionMinor, buildID, concatAuthors);
        }
    }

    /**
     * Log app info to LogInfo.
     * @param isImportant if you want it to stand out in the log
     */
    public static void logGameInfo(boolean isImportant)
    {
        if(isImportant)
        {
            Thing.LogImportant(getAppInfo());
            return;
        }
        Thing.LogInfo(getAppInfo());
    }

    public static String getChangeLog() {
        return changeLog;
    }
}
