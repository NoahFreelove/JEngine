package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

import com.JEngine.PrimitiveTypes.Position.Transform;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

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
        return defaultImage == null? new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        } : defaultImage;
    }
}
