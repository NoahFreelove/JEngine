package com.JEngine.PrimitiveTypes;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import javafx.scene.image.Image;

import java.io.File;

/** JImage (c) Noah Freelove
 * JImage is a way to convert images to sprites and ui objects
 **/

public class GameImage extends Thing {
    private Image image;
    private int xSize;
    private int ySize;

    /**
     * Default JImage constructor
     * @param isActive if the image is active by default. Will not render if not Active
     * @param filepath filepath for the image
     * @param xSize image x size
     * @param ySize image y size
     */
    public GameImage(boolean isActive, String filepath, int xSize, int ySize) {
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

    /**
     * Create an image with just a filepath, and size
     * @param filepath filepath for the image
     * @param xSize image x size
     * @param ySize image y size
     */
    public GameImage(String filepath, int xSize, int ySize) {
        super(true);
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

    // Create a JImage with a JavaFX image
    public GameImage(Image image)
    {
        super(true);
        this.image = image;
        this.xSize = (int)image.getWidth();
        this.ySize = (int)image.getHeight();
    }

    // Create a JImage with a File
    public GameImage(File file)
    {
        super(true);
        if(file.exists())
        {
            image = new Image(file.getAbsolutePath());
            return;
        }
        this.ySize = (int)image.getHeight();
        this.xSize = (int)image.getWidth();
        LogWarning(String.format("Image File: '%s' does not exist!", file.getAbsolutePath()));
    }

    // Create a JImage with a filepath
    public GameImage(String filepath)
    {
        super(true);
        Image tmpImage = null;
        File imgFile = new File(filepath);
        if(imgFile.exists())
        {
            tmpImage = new Image(filepath);
            this.image = tmpImage;
            return;
        }
        try {
            assert false;
            this.xSize = (int)tmpImage.getWidth();
            this.ySize = (int)tmpImage.getHeight();

        }catch (Exception ignore)
        {
            this.xSize = 64;
            this.ySize = 64;
        }

        LogWarning(String.format("Image File: '%s' does not exist!", filepath));
    }

    /**
     * Get x size of image
     * @return image x size
     */
    public int getWidth(){return xSize;}
    /**
     * Get y size of image
     * @return image y size
     */
    public int getHeight(){return ySize;}

    /**
     * Get Image object
     * @return javafx.scene.image.Image
     */
    public Image getImage() {return image;}

    /**
     * Set the image. Takes Image object, not filepath
=    */
    public void setImage(Image newImage) {image = newImage;}

}
