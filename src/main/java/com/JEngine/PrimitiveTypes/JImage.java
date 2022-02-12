package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import javafx.scene.image.Image;

import java.io.File;

/** JEngine.Object (c) Noah Freelove
 * Brief Explanation:
 * JImage is a way to extend from the ImageIcon class from Javax.swing
 *
 * Usage:
 * JImage is used primarily for sprites and UI images
 * **/

public class JImage extends Thing {
    private Image image;
    private final int xSize;
    private final int ySize;
    public JImage(boolean isActive, String filepath, int xSize, int ySize) {
        super(isActive);
        this.xSize = xSize;
        this.ySize = ySize;
        File imgFile = new File(filepath);
        if(imgFile.exists())
        {
            image = new Image(filepath);
            return;
        }
        LogWarning(String.format("Image File: '%s' does not exist!", filepath));
    }

    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}

    public Image getImage() {return image;}

    public void setImage(Image newImage) {image = newImage;}

}
