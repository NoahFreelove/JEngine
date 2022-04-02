package com.JEngine.Utility.Misc;

/** JDo (c) Noah Freelove
 * Run a function x number of times.
 */
public class JDo {
    /**
     * Do a method x number of times
     * @param method Method to call each loop
     * @param times Times to call the method
     * @param args Args to pass through into the method
     */
    public static void Do(GenericMethodCall method, int times, Object[] args)
    {
        for (int i = 0; i < times; i++) {
            method.call(args);
        }
    }
}
