package com.JEngine.Utility.Misc;

import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Identity;
import com.JEngine.Core.GameObject;
import javafx.scene.text.Text;

/** FPSCounter (c) Noah Freelove
 * Create a JavaFX text that updates every second and displays the current FPS.
 */
public class FPSCounter extends GameObject {

    private static int framesSinceLastSecond;
    private static int avgFPS;
    private static final GameTimer resetFPS = new GameTimer(1000, new GenericMethod[]{args -> reset()});
    public Text fpsText;

    public FPSCounter() {
        super(Transform.exSimpleTransform(0,0), new Identity("FPSCounter", "generatedUI"));
        fpsText = createFPSText();
    }

    /**
     * Update text to show the current FPS.
     */
    @Override
    public void Update(){
        fpsText.setText(String.format("FPS: %d", avgFPS));
    }

    /**
     * @return the avgFPS
     */
    public static float getFPS(){
        return avgFPS;
    }

    // Reset the frames since last second to 0 and calculate the average FPS.
    private static void reset()
    {
        avgFPS = framesSinceLastSecond;
        framesSinceLastSecond = 0;
    }
    // Start fps counter
    public static void start(){
        resetFPS.start();
    }

    // Stop counting fps
    public static void stop(){
        resetFPS.stop();
    }

    // Add frame count
    public static void updateFrame(){
        framesSinceLastSecond++;
    }

    /**
     * Create text object which displays the current FPS.
     * @return the text object created
     */
    public static Text createFPSText()
    {
        Text text = new Text("FPS: " + getFPS());
        // position the text
        text.setX(1920 - 80);
        text.setY(25);
        text.setStyle("-fx-font-size: 20px;");
        return text;
    }
}