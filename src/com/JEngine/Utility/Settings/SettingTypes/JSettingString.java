package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

public class JSettingString extends JSetting {
    String value;

    public JSettingString(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public Object getValue() {return value;}

    @Override
    public void setValue(Object newValue)
    {
        value = (String) newValue;
    }
}
