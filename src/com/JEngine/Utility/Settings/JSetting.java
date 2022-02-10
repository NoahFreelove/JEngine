package com.JEngine.Utility.Settings;

public class JSetting {
    private final String settingName;

    public JSetting(String settingName) {
        this.settingName = settingName;
    }
    public void setValue(Object newValue) { }
    public Object getValue() { return new Object(); }
    public String getName() { return settingName;}
}
