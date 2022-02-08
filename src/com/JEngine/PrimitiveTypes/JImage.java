package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import javax.swing.*;
import java.awt.*;

/** JEngine.Object (c) Noah Freelove
 * Brief Explanation:
 * JImage is a way to extend from the ImageIcon class from Javax.swing
 *
 * Usage:
 * JImage is used primarily for sprites and UI images
 * **/

public class JImage extends Thing {
    private ImageIcon image;
    public JImage(boolean isActive, String filepath) {
        super(isActive);
        image = new ImageIcon(filepath) {
        };
    }

    public ImageIcon getImage() {return image;}
    public void setImage(ImageIcon newImage) {image = newImage;}
}
