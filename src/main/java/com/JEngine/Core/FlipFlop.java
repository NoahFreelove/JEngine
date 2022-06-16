package com.JEngine.Core;

/**
 *  FlipFlop is a class that is used to create a flip flop.
 *  When the value of the flip flop is accessed, it will flip to the opposite value.
 *  This is used to create a toggle.
 * @author Noah Freelove
 */
public class FlipFlop {
    private boolean state;

    public FlipFlop() {
        state = false;
    }

    public FlipFlop(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        flip();
        return !state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void flip() {
        state = !state;
    }
}
