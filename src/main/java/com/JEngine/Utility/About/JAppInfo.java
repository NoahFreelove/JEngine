package com.JEngine.Utility.About;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogImportant;
import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogInfo;

public class JAppInfo {
    public static String appName;
    public static String[] authors;
    public static int year;
    public static int appVersionMajor;
    public static float appVersionMinor;
    public static boolean isCopyright;
    public static String buildID;


    public static String getAppInfo()
    {
        if(authors == null)
            return "JAppInfo_Error_Null_Author";
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
    public static void logAppInfo(boolean isImportant)
    {
        if(isImportant)
        {
            LogImportant(getAppInfo());
            return;
        }
        LogInfo(getAppInfo());
    }
}
