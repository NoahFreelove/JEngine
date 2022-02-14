package com.JEngine.Utility.About;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

public class JAppInfo extends Thing {
    public String appName;
    public String author;
    public int year;
    public int appVersionMajor;
    public int appVersionMinor;
    public boolean isCopyright;

    public JAppInfo(String appName, String author, int year, int appVersionMajor, int appVersionMinor, boolean isCopyright) {
        super(true);
        this.appName = appName;
        this.author = author;
        this.year = year;
        this.appVersionMajor = appVersionMajor;
        this.appVersionMinor = appVersionMinor;
        this.isCopyright = isCopyright;
    }

    public String getInfo()
    {
        if(isCopyright)
        {
            return String.format("%s : %s : Copyright %d : Version %d.%d", appName, author, year, appVersionMajor, appVersionMinor);
        }
        else {
            return String.format("%s : %s : Version %d.%d", appName, author, appVersionMajor, appVersionMinor);
        }
    }
    public void logInfo()
    {
        LogInfo(getInfo());
    }
}