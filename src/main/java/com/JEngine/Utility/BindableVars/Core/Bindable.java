package com.JEngine.Utility.BindableVars.Core;

public class Bindable{
    private Object value;
    private final String name;
    private final BindManager manager;
    private BindEvent[] events = new BindEvent[0];

    public Bindable(Object defaultValue, String name, BindManager manager) {
        this.value = defaultValue;
        this.name = name;
        this.manager = manager;
        manager.addValue(this);
    }

    void onChange(Object value) {
        this.value = value;
        for (BindEvent event : events) {
            event.onChanged(this);
        }
    }

    public void setValue(Object value) {
        manager.setValue(getName(), value);
    }

    // get name
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void addBindEvent(BindEvent event) {
        if (events == null) {
            events = new BindEvent[1];
            events[0] = event;
        } else {
            BindEvent[] newEvents = new BindEvent[events.length + 1];
            System.arraycopy(events, 0, newEvents, 0, events.length);
            newEvents[events.length] = event;
            events = newEvents;
        }
    }

}
