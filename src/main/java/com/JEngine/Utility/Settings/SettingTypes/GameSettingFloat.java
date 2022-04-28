package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.GameSetting;

/** JSettingFloat (c) Noah Freelove
 * Holds a float value that has events for changing and getting the value.
 */

public class GameSettingFloat extends GameSetting {
    float value;

    public GameSettingFloat(String name, float value) {
        super(name);
        this.value = value;
    }

    @Override
    public Object getValue() {return value;}

    @Override
    public void setValue(Object newValue)
    {
        value = (float) newValue;
        onValueChanged();
    }
}
