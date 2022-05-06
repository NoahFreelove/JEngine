package com.JEngine.Utility.ImageProcessing;

import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.JEngine.Core.Thing.LogError;

/** Screenshot (c) Noah Freelove
 * Take a screenshot of the current window, save to file or return as BufferedImage.
 */
public class Screenshot {

    /**
     * Take a screenshot of the current window.
     * @return BufferedImage of the current window.
     */
    public static BufferedImage takeScreenshot()
    {
        BufferedImage image = null;
        try {
            GameWindow window = SceneManager.getWindow();
            Rectangle screenRect = new Rectangle((int) window.getStage().getX()+10,(int) window.getStage().getY(),(int)(1280*window.getScaleMultiplier()),(int)(720*window.getScaleMultiplier()));
            image = new Robot().createScreenCapture(new Rectangle(screenRect));
        }catch (Exception ignored) {
            LogError("Failed to take screenshot");
        }
        return image;
    }

    /**
     * Save a screenshot of the current window to a file.
     * @param path Path to save the screenshot to.
     */
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

