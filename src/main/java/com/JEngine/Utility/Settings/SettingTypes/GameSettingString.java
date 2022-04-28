package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.GameSetting;

/** JSettingString (c) Noah Freelove
 * Holds a string value that has events for changing and getting the value.
 */

public class GameSettingString extends GameSetting {
    String value;

    public GameSettingString(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public Object getValue() {return value;}

    @Override
    public void setValue(Object newValue)
    {
        value = (String) newValue;
        onValueChanged();
    }
}
