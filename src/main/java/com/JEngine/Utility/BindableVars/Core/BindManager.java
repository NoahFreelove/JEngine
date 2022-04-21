package com.JEngine.Utility.BindableVars.Core;

public class BindManager {
    private Bindable[] bindings;
    String managerName = "Manager";

    public void setValue(String name, Object value) {
        int i = 0;
        for (Bindable binding : bindings) {
            if(binding == null)
                continue;
            if (binding.getName().equals(name)) {
                bindings[i].onChange(value);
            }
            i++;
        }
    }

    public void addValue(Bindable binding) {

        // if no space in array, create new one and copy data
        if (bindings == null) {
            bindings = new Bindable[1];
            bindings[0] = binding;
        } else {
            Bindable[] newBindings = new Bindable[bindings.length + 1];
            System.arraycopy(bindings, 0, newBindings, 0, bindings.length);
            newBindings[bindings.length] = binding;
            bindings = newBindings;
        }

        for (int i = 0; i < bindings.length; i++) {
            if (bindings[i] == null) {
                bindings[i] = binding;
                return;
            }
        }
    }

    public Bindable getBinding(String name) {
        for (Bindable binding : bindings) {
            if (binding == null)
                continue;
            if (binding.getName().equals(name))
                return binding;
        }
        System.out.println("No binding found with name: " + name);
        return null;
    }

    public BindManager(Bindable[] bindings) {
        this.bindings = bindings;
    }
    public BindManager(int bindingLength) {
        this.bindings = new Bindable[bindingLength];
    }

    public BindManager(String managerName) {
        this.managerName = managerName;
    }

    public BindManager(Bindable[] bindings, String managerName) {
        this.bindings = bindings;
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }
}
