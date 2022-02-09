package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

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
}
