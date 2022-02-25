package com.example;

import com.JEngine.Utility.Settings.JSetting;
import com.JEngine.Utility.Settings.JSettingCategory;
import com.JEngine.Utility.Settings.JSettingManager;
import com.JEngine.Utility.Settings.SettingTypes.JSettingFloat;
import com.JEngine.Utility.Settings.SettingTypes.JSettingString;

public class CustomSettings {
    // Make sure settings are static, so they can be accessed anywhere.
    public static JSetting[] generalSettings = new JSetting[]{
            new JSettingString("Name", "username"),
            new CustomBoolSetting("Bool Set", true)
    };
    public static JSetting[] audioSettings = new JSetting[]{
            new JSettingFloat("volume", 50f)
    };

    public static JSettingCategory[] settingCategories = new JSettingCategory[]{
            new JSettingCategory("General Settings", generalSettings),
            new JSettingCategory("Audio Settings", audioSettings),
    };

    public static JSettingManager settingManager = new JSettingManager(settingCategories);

}
