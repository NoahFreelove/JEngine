package com.JEngine.Utility.Settings;

import com.JEngine.Utility.Misc.GenericMethodCall;

public class JSetting {
    private final String settingName;
    GenericMethodCall valueChangedEvent = null;

    /**
     * Creates a new generic setting
     * @param settingName Setting name to use
     */
    public JSetting(String settingName) {
        this.settingName = settingName;
    }

    /**
     * Set the setting's value
     * @param newValue new value to set
     */
    public void setValue(Object newValue) {onValueChanged();}

    /**
     * Get the setting's value
     * @return the setting's value
     */
    public Object getValue() { return new Object(); }

    /**
     * get the setting's name
     * @return the setting's name
     */
    public String getName() { return settingName;}

    /**
     * set the event called when the value is updated
     * @param w Event to call
     */
    public void setEventValueChanged(GenericMethodCall w)
    {
        valueChangedEvent = w;
    }

    /**
     * Is called when the value is changed
     */
    protected void onValueChanged(){
        if(valueChangedEvent != null)
        {
            valueChangedEvent.call(new Object[]{getValue()});
        }
    }
}
