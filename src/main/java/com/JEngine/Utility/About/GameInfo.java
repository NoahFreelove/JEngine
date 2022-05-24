package com.JEngine.Utility.About;

import com.JEngine.Core.Thing;

/** GameInfo (c) Noah Freelove
 * Easily store and get your application's information.
 */

public class GameInfo {
    private static String appName;
    private static String[] authors;
    private static int year;
    private static int appVersionMajor;
    private static float appVersionMinor;
    private static boolean isCopyright;
    private static String buildID;
    private static String changeLog;
    /**
     * Get the app info neatly formatted in a string.
     * @return The app's info
     */
    public static String getAppInfo()
    {
        if(authors == null)
            return "GameInfo Author is Null!";
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

    public static String getAppName() {
        return appName;
    }

    public static String[] getAuthors() {
        return authors;
    }

    public static int getYear() {
        return year;
    }

    public static int getAppVersionMajor() {
        return appVersionMajor;
    }

    public static float getAppVersionMinor() {
        return appVersionMinor;
    }

    public static boolean isIsCopyright() {
        return isCopyright;
    }

    public static String getBuildID() {
        return buildID;
    }

    public static void setAppName(String appName) {
        GameInfo.appName = appName;
    }

    public static void setAuthors(String[] authors) {
        GameInfo.authors = authors;
    }

    public static void setYear(int year) {
        GameInfo.year = year;
    }

    public static void setAppVersionMajor(int appVersionMajor) {
        GameInfo.appVersionMajor = appVersionMajor;
    }

    public static void setAppVersionMinor(float appVersionMinor) {
        GameInfo.appVersionMinor = appVersionMinor;
    }

    public static void setIsCopyright(boolean isCopyright) {
        GameInfo.isCopyright = isCopyright;
    }

    public static void setBuildID(String buildID) {
        GameInfo.buildID = buildID;
    }

    public static void setChangeLog(String changeLog) {
        GameInfo.changeLog = changeLog;
    }
}
