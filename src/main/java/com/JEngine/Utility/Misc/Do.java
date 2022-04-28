package com.JEngine.Utility.Misc;

/** Do (c) Noah Freelove
 * Run a function x number of times.
 */
public class Do {
    /**
     * Do a method x number of times
     * @param method Method to call each loop
     * @param times Times to call the method
     * @param args Args to pass through into the method
     */
    public static void Do(GenericMethod method, int times, Object[] args)
    {
        while (times-- > 0)
        {
            method.call(args);
        }
    }
}
