package com.JEngine.Utility.Settings;

public class JSettingManager {
    static JSettingCategory[] settingCategories;

    public static void init(JSettingCategory[] categories){
        settingCategories = categories;
    }

    public JSettingCategory[] getSettingCategories() {return settingCategories;}

    public static JSettingCategory getSettingCategoryByName(String name)
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

    public static JSetting getSpecificSetting(String categoryName, String settingName)
    {
        JSettingCategory category = getSettingCategoryByName(categoryName);
        if(category == null)
        {
            return null;
        }

        JSetting setting = category.getSettingByName(settingName);

        if(setting == null)
        {
            return null;
        }

        return setting;
    }

    public void saveSettings(String filepath){}

    public void loadSettings(String filepath){}
}
