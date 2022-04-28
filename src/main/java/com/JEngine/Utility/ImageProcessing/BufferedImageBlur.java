package com.JEngine.Utility.ImageProcessing;

import com.JEngine.PrimitiveTypes.GameImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.Kernel;
import java.util.Arrays;

/** BufferedImageBlur (c) Noah Freelove
 * Blur a JImage, JavaFX image, or BufferedImage.
 */
public class BufferedImageBlur {

    static BufferedImage img;
    static float weight;
    static float[] data;

    /**
     * Blur an image using a Gaussian kernel.
     * @param img The image to blur.
     * @param radius The radius of the Gaussian kernel.
     * @return The blurred image.
     */
    public static BufferedImage blurBuffered(BufferedImage img, int radius)
    {
        int size = radius * 2 + 1;
        weight = 1.0f / (size * size);
        data = new float[size * size];

        try {
            Arrays.fill(data, weight);
            Kernel kernel = new Kernel(size, size, data);
            BufferedImageOp op = new ConvolveWithEdgeOp(kernel, 2, null);

            BufferedImage blurred = op.filter(img, BufferedImageBlur.img);
            Thing.LogDebug("Successfully blurred image");
            return blurred;

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    /**
     * Blur an image using a Gaussian kernel.
     * @param fxImg The image to blur.
     * @param radius The radius of the Gaussian kernel.
     * @return The blurred image.
     */
    public static Image blurImage(Image fxImg, int radius)
    {
        BufferedImage img = SwingFXUtils.fromFXImage(fxImg, null);

        int size = radius * 2 + 1;
        weight = 1.0f / (size * size);
        data = new float[size * size];

        try {
            Arrays.fill(data, weight);
            Kernel kernel = new Kernel(size, size, data);
            BufferedImageOp op = new ConvolveWithEdgeOp(kernel, 2, null);

            BufferedImage blurred = op.filter(img, BufferedImageBlur.img);
            Thing.LogDebug("Successfully blurred image");

            return SwingFXUtils.toFXImage(blurred, null);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    /**
     * Blur an image using a Gaussian kernel.
     * @param gameImage The image to blur.
     * @param radius The radius of the Gaussian kernel.
     * @return The blurred image.
     */
    public static GameImage blurImage(GameImage gameImage, int radius)
    {
        return new GameImage(blurImage(gameImage.getImage(), radius));
    }
}