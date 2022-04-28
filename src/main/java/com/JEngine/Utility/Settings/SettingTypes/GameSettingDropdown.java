package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.GameSetting;

/** JSettingDropdown (c) Noah Freelove
 * Holds a string[] value that has events for changing and getting the value.
 * In addition to the option string[], it also has selected value
 */

public class GameSettingDropdown extends GameSetting {
    public String[] dropdownOptions;
    public int selectedOption;

    public GameSettingDropdown(String settingName, String[] options, int defaultOption) {
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
