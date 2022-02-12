package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

import com.JEngine.PrimitiveTypes.Position.Transform;

import javafx.scene.image.Image;

public class JUIObject extends JObject{
    Image defaultImage = null;
    public int sizeX = 128;
    public int sizeY = 128;

    public JUIObject(Transform transform, com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity JIdentity, int sizeX, int sizeY) {
        super(transform, JIdentity);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public JUIObject(Transform transform, com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity JIdentity, Image defaultImage, int sizeX, int sizeY) {
        super(transform, JIdentity);
        this.defaultImage = defaultImage;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public Image getImage()
    {
        return defaultImage == null? null : defaultImage;
    }
}
