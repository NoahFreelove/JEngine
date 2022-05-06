package com.JEngine.PrimitiveTypes;

import java.io.Serializable;

/** Identity (c) Noah Freelove
 * Brief Explanation:
 * A Identity holds information about an object that helps to distinguish it from the rest.
 * A Identity holds a name and a tag.
 * This lets you log the name of a specific object, get all objects in a scene by tag or by name
 *
 * Every object has an identity.
 * It is strongly recommended that you always fill out the identities uniquely, so you don't end up with two objects
 * which you can't tell apart.
 * **/

public class Identity implements Serializable {
    private String name;
    private String tag;

    /**
     * Default constructor
     * @param name The name of the object
     * @param tag  The tag of the object
     */
    public Identity(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }
    // Setters
    public void setName(String newName){name = newName;}
    public void setTag(String newTag){tag = newTag;}

    // Getters
    public String getName(){return name;}
    public String getTag(){return tag;}

    // Comparers
    public boolean compareTag(String tagToCompare) {return (tag.equals(tagToCompare));}
    public boolean compareName(String nameToCompare) {return (name.equals(nameToCompare));}

    // Make toString readable
    public String toString()
    {
        return getName() + " : " + getTag();
    }
}
