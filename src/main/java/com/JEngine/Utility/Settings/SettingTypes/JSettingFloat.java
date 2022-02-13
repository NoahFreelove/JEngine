package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

public class JSettingFloat extends JSetting {
    float value;

    public JSettingFloat(String name, float value) {
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
