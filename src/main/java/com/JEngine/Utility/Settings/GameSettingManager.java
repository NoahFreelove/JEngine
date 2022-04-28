package com.JEngine.Utility.Settings;

/** JSetting (c) Noah Freelove
 * Holds an array of JSettingCategory objects.
 * It is static, so you need to initialize it before using it.
 */

public class GameSettingManager {
    static GameSettingCategory[] settingCategories;

    // make sure to init with the categories!
    public static void init(GameSettingCategory[] categories){
        settingCategories = categories;
    }

    /**
     * Get the settings categories
     * @return settings categories
     */
    public GameSettingCategory[] getSettingCategories() {return settingCategories;}

    /**
     * Get a setting category by name
     * @param name name of the category
     * @return the category
     */
    public static GameSettingCategory getSettingCategoryByName(String name)
    {
        for (GameSettingCategory category :
                settingCategories) {
            if(category.getCategoryName().equals(name))
            {
                return category;
            }
        }
        return null;
    }

    /**
     * Get a specific setting in a category and by name
     * @param settingName name of the setting
     * @param categoryName name of the category
     * @return the setting
     */
    public static GameSetting getSpecificSetting(String categoryName, String settingName)
    {
        GameSettingCategory category = getSettingCategoryByName(categoryName);
        if(category == null)
        {
            return null;
        }

        GameSetting setting = category.getSettingByName(settingName);

        if(setting == null)
        {
            return null;
        }

        return setting;
    }

    /**
     * Save the settings to a file
     * @param filepath filepath to save to
     * Not implemented yet
     */
    public void saveSettings(String filepath){}

    /**
     * Load the settings from a file
     * @param filepath filepath to load from
     * Not implemented yet
     */
    public void loadSettings(String filepath){}
}
