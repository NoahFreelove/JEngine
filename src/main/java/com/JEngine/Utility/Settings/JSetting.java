package com.JEngine.Utility.Settings;

public class JSetting {
    private final String settingName;
    ValueChangedEvent valueChangedEvent = null;

    public JSetting(String settingName) {
        this.settingName = settingName;
    }
    public void setValue(Object newValue) {onValueChanged();}

    public Object getValue() { return new Object(); }
    public String getName() { return settingName;}

    public void setEventValueChanged(ValueChangedEvent w)
    {
        valueChangedEvent = w;
    }

    protected void onValueChanged(){
        if(valueChangedEvent != null)
        {
            valueChangedEvent.valueChanged();
        }
    }
}
