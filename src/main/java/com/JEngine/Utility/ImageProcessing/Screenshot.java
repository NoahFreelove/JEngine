package com.JEngine.Utility.ImageProcessing;

import com.JEngine.Game.Visual.JWindow;
import com.JEngine.Game.Visual.Scenes.JSceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogError;

public class Screenshot {

    public static BufferedImage takeScreenshot()
    {
        BufferedImage image = null;
        try {
            JWindow window = JSceneManager.getWindow();
            Rectangle screenRect = new Rectangle((int) window.getStage().getX()+10,(int) window.getStage().getY(),(int)(1280*window.getScaleMultiplier()),(int)(720*window.getScaleMultiplier()));
            image = new Robot().createScreenCapture(new Rectangle(screenRect));
        }catch (Exception ignored) {
            LogError("Failed to take screenshot");
        }
        return image;
    }

    public static void saveScreenshot(String path)
    {
        try {
            BufferedImage image = takeScreenshot();
            ImageIO.write(image, "png", new File(path));
        }catch (Exception ignored) {
            LogError("Failed to save screenshot");
        }
    }
}

