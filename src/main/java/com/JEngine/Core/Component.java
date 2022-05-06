package com.JEngine.Core;

/** Component (c) Noah Freelove
 * Brief Explanation:
 * Components can be applied to any GameObject and are used to extend the functionality of the GameObject.
 * Components are added to the GameObjects via the GameObject's addComponent() method.
 * Components are removed from the GameObjects via the GameObject's removeComponent() method.
 * Components have their own Update() method which is called automatically when attached to a GameObject.
 *
 * By default, Components have fields which aren't required but could be useful.
 * **/
public class Component extends Thing {
    private String componentName;
    private Object[] fields = new Object[0];
    private GameObject parent = null;


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

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
