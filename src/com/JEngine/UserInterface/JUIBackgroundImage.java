package com.JEngine.UserInterface;

import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;

import java.awt.*;

public class JUIBackgroundImage extends JUIObject {
    public JUIBackgroundImage(JIdentity JIdentity, Image image, int sizeX, int sizeY) {
        // set sizeX and sizeY to camera FOV or window size
        super(new Transform(new Vector3(0,0,0), new Vector3(0,0,0), new Vector3(1,1,1)), JIdentity, image, sizeX, sizeY);
    }
}
