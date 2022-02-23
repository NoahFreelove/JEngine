package com.JEngine.Utility.About;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

public class JAppInfo extends Thing {
    public String appName;
    public String author;
    public String[] authors;
    private String concatAuthors;
    private boolean multipleAuthors;
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

    public JAppInfo(String appName, String[] authors, int year, int appVersionMajor, int appVersionMinor, boolean isCopyright) {
        super(true);
        this.appName = appName;
        this.authors = authors;
        this.year = year;
        this.appVersionMajor = appVersionMajor;
        this.appVersionMinor = appVersionMinor;
        this.isCopyright = isCopyright;
        this.concatAuthors = "";
        multipleAuthors = true;
        int i = 1;
        for (String a :
                authors) {
            if(i == authors.length)
            {
                concatAuthors += a;
                break;
            }
            concatAuthors += (a + " : ");
            i++;
        }
    }

    public String getInfo()
    {
        if(isCopyright)
        {
            if (!multipleAuthors)
            {
                return String.format("%s : %s : Copyright %d : Version %d.%d", appName, author, year, appVersionMajor, appVersionMinor);
            }
            return String.format("%s : Copyright %d : Version %d.%d\nCreated by, %s", appName, year, appVersionMajor, appVersionMinor, concatAuthors);
        }
        else {
            if (!multipleAuthors)
            {
                return String.format("%s : %s : %d : Version %d.%d", appName, author, year, appVersionMajor, appVersionMinor);

            }
            return String.format("%s : %d : Version %d.%d\nCreated by, %s", appName, year, appVersionMajor, appVersionMinor, concatAuthors);
        }
    }
    public void logInfo()
    {
        LogInfo(getInfo());
    }
}
