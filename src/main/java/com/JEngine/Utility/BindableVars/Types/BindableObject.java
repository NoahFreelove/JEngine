package com.JEngine.Utility.BindableVars.Types;

import com.JEngine.Utility.BindableVars.Core.BindManager;
import com.JEngine.Utility.BindableVars.Core.Bindable;

public class BindableObject extends Bindable {

    public BindableObject(Object defaultValue, String name, BindManager manager) {
        super(defaultValue, name, manager);
    }
    @Override
    public String getName() {
        return super.getName();
    }
}
