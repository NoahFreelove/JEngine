package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

/** JSettingInt (c) Noah Freelove
 * Holds an int value that has events for changing and getting the value.
 */
public class JSettingInt extends JSetting {
    int value;

    public JSettingInt(String name, int value) {
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
