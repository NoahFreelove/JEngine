package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

/** JEngine.JIdentity (c) Noah Freelove
 * Brief Explanation:
 * A JIdentity holds information about an object that helps to distinguish it from the rest.
 * A JIdentity holds a name and a tag.
 * This lets you log the name of a specific object, get all objects in a scene by tag or by name
 *
 * Every object has an identity.
 * It is strongly recommended that you always fill out the identities uniquely, so you don't end up with two objects
 * which you can't tell apart.
 * **/

public class JIdentity {
    String name;
    String tag;

    public JIdentity(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public void setName(String newName){name = newName;}
    public void setTag(String newTag){tag = newTag;}
    public String getName(){return name;}
    public String getTag(){return tag;}
    public boolean compareTag(String tagToCompare) {return (tag.equals(tagToCompare));}
    public boolean compareName(String nameToCompare) {return (name.equals(nameToCompare));}

}
