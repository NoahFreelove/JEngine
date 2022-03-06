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


    public static String getInfo()
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
                return String.format("%s : %s : Copyright %d : Version %d.%.2f", appName, authors[0], year, appVersionMajor, appVersionMinor);
            }

            return String.format("%s : Copyright %d : Version %d.%.2f\nCreated by: %s", appName, year, appVersionMajor, appVersionMinor, concatAuthors.toString());
        }
        else {
            if (!multipleAuthors)
            {
                return String.format("%s : %s : %d : Version %d.%.2f", appName, authors[0], year, appVersionMajor, appVersionMinor);

            }
            return String.format("%s : %d : Version %d.%.2f\nCreated by: %s", appName, year, appVersionMajor, appVersionMinor, concatAuthors.toString());
        }
    }
    public static void logInfo(boolean isImportant)
    {
        if(isImportant)
        {
            LogImportant(getInfo());
            return;
        }
        LogInfo(getInfo());
    }
}
