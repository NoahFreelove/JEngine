package com.JEngine.Utility.BindableVars.Types;

import com.JEngine.Utility.BindableVars.Core.BindManager;
import com.JEngine.Utility.BindableVars.Core.Bindable;

public class BindableString extends Bindable {

    public BindableString(String defaultValue, String name, BindManager manager) {
        super(defaultValue, name, manager);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getValue(){
        return (String)super.getValue();
    }
}
