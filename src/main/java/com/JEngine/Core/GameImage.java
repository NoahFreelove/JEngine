package com.JEngine.Core;

import com.JEngine.Utility.ImageProcessing.MissingTexture;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

import java.io.File;

/** GameImage (c) Noah Freelove
 * GameImage is a way to convert images to sprites and ui objects
 **/

public class GameImage extends Thing {
    private Image image;
    private int xSize;
    private int ySize;
    private boolean tiled;
    private int tileSizeX;
    private int tileSizeY;
    private ColorAdjust colorAdjust = new ColorAdjust();

    /**
     * Default GameImage constructor
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
        image = MissingTexture.getMissingTextureImage();
        this.ySize = 128;
        this.xSize = 128;
    }

    public GameImage(GameImage newGameImage) {
        super(true);
        this.xSize = newGameImage.xSize;
        this.ySize = newGameImage.ySize;
        this.image = newGameImage.image;
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
            image = new Image(imgFile.getAbsolutePath());
            return;
        }
        LogWarning(String.format("Image File: '%s' does not exist!", filepath));
        image = MissingTexture.getMissingTextureImage();
        this.ySize = 128;
        this.xSize = 128;
    }

    // Create an Image with a JavaFX image
    public GameImage(Image image)
    {
        super(true);
        this.image = image;
        this.xSize = (int)image.getWidth();
        this.ySize = (int)image.getHeight();
    }

    // Create an Image with a File
    public GameImage(File file)
    {
        super(true);
        if(file.exists())
        {
            image = new Image(file.getAbsolutePath());
            this.ySize = (int)image.getHeight();
            this.xSize = (int)image.getWidth();
            return;
        }
        LogWarning(String.format("Image File: '%s' does not exist!", file.getAbsolutePath()));
        image = MissingTexture.getMissingTextureImage();
        this.ySize = 128;
        this.xSize = 128;
    }

    // Create an Image with a filepath
    public GameImage(String filepath)
    {
        this(new File(filepath));
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

    public boolean isTiled() {
        return tiled;
    }

    public void setTiled(boolean tiled) {
        this.tiled = tiled;
    }

    public int getTileSizeX() {
        return tileSizeX;
    }

    public void setTileSizeX(int tileX) {
        this.tileSizeX = tileX;
    }

    public int getTileSizeY() {
        return tileSizeY;
    }

    public void setTileSizeY(int tileY) {
        this.tileSizeY = tileY;
    }

    public ColorAdjust getColorAdjust() {
        return colorAdjust;
    }

    public void setColorAdjust(ColorAdjust colorAdjust) {
        this.colorAdjust = colorAdjust;
    }
}
