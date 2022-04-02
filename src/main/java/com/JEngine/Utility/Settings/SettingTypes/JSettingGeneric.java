package com.JEngine.Utility.Settings.SettingTypes;

import com.JEngine.Utility.Settings.JSetting;

/** JSettingGeneric (c) Noah Freelove
 * Holds an object value that has events for changing and getting the value.
 */

public class JSettingGeneric extends JSetting{
    private Object value;

    public JSettingGeneric(String name, Object value) {
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
