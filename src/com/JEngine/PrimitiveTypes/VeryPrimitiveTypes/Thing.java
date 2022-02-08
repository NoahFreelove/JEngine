package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

/** JEngine.Thing (c) Noah Freelove
 * Brief Explanation:
 * A Thing is the most basic type in JEngine. It does not require a transform, just an active state.
 * **/

public class Thing {
    boolean isActive;

    public Thing(boolean isActive) {
        this.isActive = isActive;
    }
}
