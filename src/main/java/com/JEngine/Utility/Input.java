package com.JEngine.Utility;

import com.JEngine.Game.Visual.JSceneManager;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
    //region Key Combos
    public static boolean Up;
    public static boolean Down;
    public static boolean Left;
    public static boolean Right;
    //endregion

    //region Keys
    public static boolean W_Pressed;
    public static boolean A_Pressed;
    public static boolean S_Pressed;
    public static boolean D_Pressed;
    public static boolean UArrow_Pressed;
    public static boolean LArrow_Pressed;
    public static boolean DArrow_Pressed;
    public static boolean RArrow_Pressed;
    //endregion

    public static void init(Scene scene) {
        if (JSceneManager.getScene() != null)
        {
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                checkKeyDown(key.getCode());
            });
            scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                checkKeyUp(key.getCode());
            });
        }
    }
    public static void checkKeyDown(KeyCode key)
    {
        switch (key)
        {
            case W -> W_Pressed = true;
            case A -> A_Pressed = true;
            case S -> S_Pressed = true;
            case D -> D_Pressed = true;
            case LEFT -> LArrow_Pressed = true;
            case UP -> UArrow_Pressed = true;
            case DOWN -> DArrow_Pressed = true;
            case RIGHT -> RArrow_Pressed = true;
        }
        checkKeyCombos();
    }
    public static void checkKeyUp(KeyCode key)
    {
        switch (key)
        {
            case W -> W_Pressed = false;
            case A -> A_Pressed = false;
            case S -> S_Pressed = false;
            case D -> D_Pressed = false;
            case LEFT -> LArrow_Pressed = false;
            case UP -> UArrow_Pressed = false;
            case DOWN -> DArrow_Pressed = false;
            case RIGHT -> RArrow_Pressed = false;
        }
        checkKeyCombos();
    }

    static void checkKeyCombos()
    {
        Up = W_Pressed||UArrow_Pressed;
        Down = S_Pressed||DArrow_Pressed;
        Left = A_Pressed||LArrow_Pressed;
        Right = D_Pressed||RArrow_Pressed;
    }

}
