package com.JEngine.Utility.ImageProcessingAndEffects;

import java.awt.image.BufferedImage;

public class MissingTexture {

    public static BufferedImage getMissingTexture(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        boolean isOffTile = false;
        // Generates a Pink and Black checkerboard pattern. Each checker is 8x8 pixels.
        for (int x = 0; x < height; x++) {
            if(x%8==0 ) {
                isOffTile = !isOffTile;
            }
            for (int y = 0; y < width; y++) {
                if(y%8==0 ) {
                    isOffTile = !isOffTile;
                }

                if (isOffTile) {
                    image.setRGB(x, y, 0xFFFF69B4);
                }
                else {
                    image.setRGB(x, y, 0xFF000000);
                }
            }
        }
        return image;
    }

    public static javafx.scene.image.Image getMissingTextureImage() {
        return SwingFXUtils.toFXImage(getMissingTexture(128,128), null);
    }

    public static javafx.scene.image.Image getMissingTextureImage(int width, int height) {
        return SwingFXUtils.toFXImage(getMissingTexture(width, height), null);
    }
}
