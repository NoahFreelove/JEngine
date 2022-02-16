package com.JEngine.Utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


// Not to be called at update!!!
public class BufferedImageBlur {

    BufferedImage img2;

    float weight;
    float[] data;

    public BufferedImage blur(BufferedImage img, int radius)
    {
        int size = radius * 2 + 1;
        weight = 1.0f / (size * size);
        data = new float[size * size];

        try {
            Arrays.fill(data, weight);
            Kernel kernel = new Kernel(size, size, data);
            BufferedImageOp op = new ConvolveWithEdgeOp(kernel, 2, null);

            BufferedImage blurred = op.filter(img, img2);
            System.out.println("Successfully blurred image");
            return blurred;

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}