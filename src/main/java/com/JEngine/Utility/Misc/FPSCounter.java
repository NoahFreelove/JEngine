package com.JEngine.Utility.Misc;

import com.JEngine.PrimitiveTypes.Position.Transform;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;
import javafx.scene.text.Text;

public class FPSCounter extends JObject {

    private static int framesSinceLastSecond;
    private static int avgFPS;
    private static final JTimer resetFPS = new JTimer(1000, new GenericMethodCall[]{args -> reset()});
    public Text fpsText;

    public FPSCounter() {
        super(Transform.exSimpleTransform(0,0), new JIdentity("FPSCounter", "generatedUI"));
        fpsText = createFPSText();
    }

    @Override
    public void Update(){
        fpsText.setText(String.format("FPS: %d", avgFPS));
    }

    public static float getFPS(){
        return avgFPS;
    }

    private static void reset()
    {
        avgFPS = framesSinceLastSecond;
        framesSinceLastSecond = 0;
    }

    public static void start(){
        resetFPS.start();
    }

    public static void stop(){
        resetFPS.stop();
    }

    public static void update(){
        framesSinceLastSecond++;
    }

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