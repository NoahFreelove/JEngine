package com.JEngine.Utility.Settings;

/** JSettingCategory (c) Noah Freelove
 * Holds an array of settings with a name.
 */
public class GameSettingCategory {
    private final String categoryName;
    GameSetting[] settings;

    public GameSettingCategory(String name, GameSetting[] settings) {
        this.categoryName = name;
        this.settings = settings;
    }

    /**
     * Get a specific setting by name in the category.
     * @param name The name of the setting.
     * @return the setting.
     */
    public GameSetting getSettingByName(String name)
    {
        for (GameSetting setting :
                settings) {
            if(setting.getName().equals(name))
            {
                return setting;
            }
        }
        return null;
    }

    /**
     * Get the name of the category.
     * @return the name of the category.
     */
    public String getCategoryName() {return categoryName;}
}
