package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import javax.swing.*;

/** JEngine.Object (c) Noah Freelove
 * Brief Explanation:
 * JImage is a way to extend from the ImageIcon class from Javax.swing
 *
 * Usage:
 * JImage is used primarily for sprites and UI images
 * **/

public class JImage extends Thing {
    private ImageIcon image;
    private int xSize;
    private int ySize;

    public JImage(boolean isActive, String filepath, int xSize, int ySize) {
        super(isActive);
        this.xSize = xSize;
        this.ySize = ySize;
        image = new ImageIcon(filepath);
    }

    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}

    public ImageIcon getImage() {return image;}
    public void setImage(ImageIcon newImage) {image = newImage;}
}
