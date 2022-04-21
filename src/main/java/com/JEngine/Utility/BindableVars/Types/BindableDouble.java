package com.JEngine.Utility.BindableVars.Types;

import com.JEngine.Utility.BindableVars.Core.BindManager;
import com.JEngine.Utility.BindableVars.Core.Bindable;

public class BindableDouble extends Bindable {
    public BindableDouble(Double defaultValue, String name, BindManager manager) {
        super(defaultValue, name, manager);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void increment(){
        setValue(getValue() + 1);
    }

    public void decrement(){
        setValue(getValue() - 1);
    }

    @Override
    public Double getValue(){
        return (Double) super.getValue();
    }
}
