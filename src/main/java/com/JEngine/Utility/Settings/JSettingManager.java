package com.JEngine.Utility.Settings;

import java.util.Objects;

import static com.Examples.Settings.settingManager;

public class JSettingManager {
    static JSettingCategory[] settingCategories;

    public JSettingManager(JSettingCategory[] settingCategories) {
        JSettingManager.settingCategories = settingCategories;
    }

    public JSettingCategory[] getSettingCategories() {return settingCategories;}

    public JSettingCategory getSettingCategoryByName(String name)
    {
        for (JSettingCategory category :
                settingCategories) {
            if(category.getCategoryName().equals(name))
            {
                return category;
            }
        }
        return null;
    }

    public Object getSpecificSetting(String categoryName, String settingName)
    {
        JSettingCategory category = getSettingCategoryByName(categoryName);
        if(category == null)
        {
            return false;
        }

        JSetting setting = category.getSettingByName(settingName);

        if(setting == null)
        {
            return false;
        }

        return setting;
    }

    public void saveSettings(String filepath){}

    public void loadSettings(String filepath){}
}
