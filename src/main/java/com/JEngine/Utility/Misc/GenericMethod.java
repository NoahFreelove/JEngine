package com.JEngine.Utility.Misc;

/** GenericMethodCall (c) Noah Freelove
 * Generic method call is an interface that allows for the calling of a generic method that takes an Object array
 * Used in JTimer, JDo, WaitForSeconds
 *
 * The method call is the way to run a series of actions or methods from another class
 */
public interface GenericMethod {
    void call(Object[] args);
}
