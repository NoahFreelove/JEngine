package com.JEngine.Utility.Settings;

public class JSettingCategory {
    public String categoryName;
    JSetting[] settings;

    public JSettingCategory(String name, JSetting[] settings) {
        this.categoryName = name;
        this.settings = settings;
    }

    public JSetting getSettingByName(String name)
    {
        for (JSetting setting :
                settings) {
            if(setting.getName().equals(name))
            {
                return setting;
            }
        }
        return null;
    }

    public String getCategoryName() {return categoryName;}
}
