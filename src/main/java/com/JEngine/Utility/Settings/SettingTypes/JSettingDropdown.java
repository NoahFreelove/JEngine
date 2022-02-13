package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

public class JSettingDropdown extends JSetting {
    public String[] dropdownOptions;
    public int selectedOption;

    public JSettingDropdown(String settingName, String[] options, int defaultOption) {
        super(settingName);
        this.dropdownOptions = options;
        selectedOption = defaultOption;
    }

    @Override
    public Object getValue() {return dropdownOptions[selectedOption];}

    public void updateSelectedOption(int newOptionIndex)
    {
        selectedOption = newOptionIndex;
    }

    @Override
    public void setValue(Object newValue)
    {
        dropdownOptions = (String[]) newValue;
        onValueChanged();
    }

    public int getNumberOfOptions() {return dropdownOptions.length;}
}
