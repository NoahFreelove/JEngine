package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.GameSetting;

/** JSettingGeneric (c) Noah Freelove
 * Holds an object value that has events for changing and getting the value.
 */

public class GameSettingGeneric extends GameSetting {
    private Object value;

    public GameSettingGeneric(String name, Object value) {
        super(name);
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
