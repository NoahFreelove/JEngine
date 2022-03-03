package com.JEngine.Utility.ImageProcessing;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;
import com.JEngine.Utility.ImageProcessing.ConvolveWithEdgeOp;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.Kernel;
import java.util.Arrays;


// Not to be called at update!!!
public class BufferedImageBlur {

    static BufferedImage img2;

    static float weight;
    static float[] data;

    public static BufferedImage blurBuffered(BufferedImage img, int radius)
    {
        int size = radius * 2 + 1;
        weight = 1.0f / (size * size);
        data = new float[size * size];

        try {
            Arrays.fill(data, weight);
            Kernel kernel = new Kernel(size, size, data);
            BufferedImageOp op = new ConvolveWithEdgeOp(kernel, 2, null);

            BufferedImage blurred = op.filter(img, img2);
            Thing.LogAnnoyance("Successfully blurred image");
            return blurred;

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
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

            BufferedImage blurred = op.filter(img, img2);
            Thing.LogAnnoyance("Successfully blurred image");

            return SwingFXUtils.toFXImage(blurred, null);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
    /**/
}