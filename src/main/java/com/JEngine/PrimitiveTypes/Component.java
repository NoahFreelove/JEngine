package com.JEngine.PrimitiveTypes;

public class Component extends Thing {
    private String componentName;
    private Object[] fields = new Object[0];
    private Object parent = null;


    public Object[] getFields() {
        return fields;
    }
    public void setFields(Object[] fields) {
        this.fields = fields;
    }

    public void addField(Object field) {
        Object[] newFields = new Object[fields.length + 1];
        for (int i = 0; i < fields.length; i++) {
            newFields[i] = fields[i];
        }
        newFields[fields.length] = field;
        fields = newFields;
    }

    public void removeField(Object field) {
        Object[] newFields = new Object[fields.length - 1];
        int index = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] != field) {
                newFields[index] = fields[i];
                index++;
            }
        }
        fields = newFields;
    }

    public Component(boolean isActive, String componentName) {
        super(isActive);
        this.componentName = componentName;
    }
    public Component(String componentName)
    {
        this(true, componentName);
    }

    public Component()
    {
        super(true);
    }

    public void Update(){}

    public String getName() {
        return componentName;
    }

    public void setName(String componentName) {
        this.componentName = componentName;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }
}
