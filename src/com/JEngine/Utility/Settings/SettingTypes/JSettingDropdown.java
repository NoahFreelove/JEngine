package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

public class JSettingDropdown extends JSetting {
    public String[] dropdownOptions;
    public JSettingDropdown(String settingName, String[] options) {
        super(settingName);
        this.dropdownOptions = options;
    }

    @Override
    public Object getValue() {return dropdownOptions;}

    @Override
    public void setValue(Object newValue)
    {
        dropdownOptions = (String[]) newValue;
    }
    public int getNumberOfOptions() {return dropdownOptions.length;}
}
