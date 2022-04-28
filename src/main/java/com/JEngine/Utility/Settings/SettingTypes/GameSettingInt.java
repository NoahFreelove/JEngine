package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.GameSetting;

/** JSettingInt (c) Noah Freelove
 * Holds an int value that has events for changing and getting the value.
 */
public class GameSettingInt extends GameSetting {
    int value;

    public GameSettingInt(String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public Object getValue() {return value;}

    @Override
    public void setValue(Object newValue)
    {
        value = (int) newValue;
        onValueChanged();
    }
}
