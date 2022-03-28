package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

public class JSettingBool extends JSetting {
    boolean enabled;

    public JSettingBool(String name, boolean enabled) {
        super(name);
        this.enabled = enabled;
    }

    @Override
    public Object getValue() {return enabled;}

    @Override
    public void setValue(Object newValue)
    {
        enabled = (boolean) newValue;
        onValueChanged();
    }
}
