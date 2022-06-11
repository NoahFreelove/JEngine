package com.JEngine.Utility.ImageProcessingAndEffects;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class GenerateSolidTexture {

    public static BufferedImage generate(int width, int height, int RGB) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i,j, RGB);
            }
        }
        return image;
    }

    /**
     * Generate a solid texture from an image
     * @param width width of the texture
     * @param height height of the texture
     * @param RGB RGB of the texture  To convert your typical hex color to RGB int, do the following:
     *            0xAARRGGBB
     *            A = Alpha, R = Red, G = Green, B = Blue
     *            ex. 0xFF0000FF is Solid blue
     * @return a JavaFX image of the solid color
     */
    public static Image generateImage(int width, int height, int RGB) {
        return  SwingFXUtils.toFXImage(generate(width, height, RGB), null);
    }
}
