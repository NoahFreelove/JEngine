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
    //region Standard Movement Keys
    public static boolean W_Pressed;
    public static boolean A_Pressed;
    public static boolean S_Pressed;
    public static boolean D_Pressed;
    public static boolean UArrow_Pressed;
    public static boolean LArrow_Pressed;
    public static boolean DArrow_Pressed;
    public static boolean RArrow_Pressed;
    //endregion

    //region Alphabet Keys
    public static boolean Z_Pressed;
    public static boolean X_Pressed;
    public static boolean C_Pressed;
    public static boolean V_Pressed;
    public static boolean B_Pressed;
    public static boolean N_Pressed;
    public static boolean M_Pressed;
    public static boolean F_Pressed;
    public static boolean G_Pressed;
    public static boolean H_Pressed;
    public static boolean J_Pressed;
    public static boolean K_Pressed;
    public static boolean L_Pressed;
    public static boolean Q_Pressed;
    public static boolean E_Pressed;
    public static boolean R_Pressed;
    public static boolean T_Pressed;
    public static boolean Y_Pressed;
    public static boolean U_Pressed;
    public static boolean I_Pressed;
    public static boolean O_Pressed;
    public static boolean P_Pressed;
    //endregion

    //region Modifier Keys
    public static boolean Shift_Pressed;
    public static boolean Control_Pressed;
    public static boolean Alt_Pressed;
    public static boolean Tab_Pressed;
    //endregion
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
            case SHIFT -> Shift_Pressed = true;
            case CONTROL -> Control_Pressed = true;
            case ALT -> Alt_Pressed = true;
            case TAB -> Tab_Pressed = true;
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
            case SHIFT -> Shift_Pressed = false;
            case CONTROL -> Control_Pressed = false;
            case ALT -> Alt_Pressed = false;
            case TAB -> Tab_Pressed = false;
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
