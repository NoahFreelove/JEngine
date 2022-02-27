package com.JEngine.Utility;

import com.JEngine.Game.Visual.JSceneManager;
import com.JEngine.Utility.Misc.GenericMethodCall;
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

    //region Number Keys
    public static boolean NumRow0_Pressed;
    public static boolean NumRow1_Pressed;
    public static boolean NumRow2_Pressed;
    public static boolean NumRow3_Pressed;
    public static boolean NumRow4_Pressed;
    public static boolean NumRow5_Pressed;
    public static boolean NumRow6_Pressed;
    public static boolean NumRow7_Pressed;
    public static boolean NumRow8_Pressed;
    public static boolean NumRow9_Pressed;
    //endregion

    //region Modifier Keys
    public static boolean Shift_Pressed;
    public static boolean Control_Pressed;
    public static boolean Alt_Pressed;
    public static boolean Tab_Pressed;
    //endregion
    //endregion

    public static GenericMethodCall[] keyDownEvents = new GenericMethodCall[200];
    public static GenericMethodCall[] keyUpEvents = new GenericMethodCall[200];

    public static void init(Scene scene) {
        if (JSceneManager.getScene() != null)
        {
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                checkKeyDown(key.getCode());
                for (GenericMethodCall g :
                        keyDownEvents) {
                    if(g !=null)
                    {
                        g.call(new Object[]{key.getCode()});
                    }
                }
            });
            scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                checkKeyUp(key.getCode());
                for (GenericMethodCall g :
                        keyUpEvents) {
                    if(g !=null)
                    {
                        g.call(new Object[]{key.getCode()});
                    }
                }
            });
        }
    }

    public static void addKeyDownEvent(GenericMethodCall event)
    {
        for (int i = 0; i < keyDownEvents.length; i++) {
            if(keyDownEvents[i] == null)
            {
                keyDownEvents[i] = event;
                break;
            }
        }
    }

    public static void addKeyUpEvent(GenericMethodCall event)
    {
        for (int i = 0; i < keyUpEvents.length; i++) {
            if(keyUpEvents[i] == null)
            {
                keyUpEvents[i] = event;
                break;
            }
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
            case DIGIT0 -> NumRow0_Pressed = true;
            case DIGIT1 -> NumRow1_Pressed = true;
            case DIGIT2 -> NumRow2_Pressed = true;
            case DIGIT3 -> NumRow3_Pressed = true;
            case DIGIT4 -> NumRow4_Pressed = true;
            case DIGIT5 -> NumRow5_Pressed = true;
            case DIGIT6 -> NumRow6_Pressed = true;
            case DIGIT7 -> NumRow7_Pressed = true;
            case DIGIT8 -> NumRow8_Pressed = true;
            case DIGIT9 -> NumRow9_Pressed = true;
            case Z -> Z_Pressed = true;
            case X -> X_Pressed = true;
            case C -> C_Pressed = true;
            case V -> V_Pressed = true;
            case B -> B_Pressed = true;
            case N -> N_Pressed = true;
            case M -> M_Pressed = true;
            case F -> F_Pressed = true;
            case G -> G_Pressed = true;
            case H -> H_Pressed = true;
            case J -> J_Pressed = true;
            case K -> K_Pressed = true;
            case L -> L_Pressed = true;
            case Q -> Q_Pressed = true;
            case E -> E_Pressed = true;
            case R -> R_Pressed = true;
            case T -> T_Pressed = true;
            case Y -> Y_Pressed = true;
            case U -> U_Pressed = true;
            case I -> I_Pressed = true;
            case O -> O_Pressed = true;
            case P -> P_Pressed = true;

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
            case DIGIT0 -> NumRow0_Pressed = false;
            case DIGIT1 -> NumRow1_Pressed = false;
            case DIGIT2 -> NumRow2_Pressed = false;
            case DIGIT3 -> NumRow3_Pressed = false;
            case DIGIT4 -> NumRow4_Pressed = false;
            case DIGIT5 -> NumRow5_Pressed = false;
            case DIGIT6 -> NumRow6_Pressed = false;
            case DIGIT7 -> NumRow7_Pressed = false;
            case DIGIT8 -> NumRow8_Pressed = false;
            case DIGIT9 -> NumRow9_Pressed = false;
            case Z -> Z_Pressed = false;
            case X -> X_Pressed = false;
            case C -> C_Pressed = false;
            case V -> V_Pressed = false;
            case B -> B_Pressed = false;
            case N -> N_Pressed = false;
            case M -> M_Pressed = false;
            case F -> F_Pressed = false;
            case G -> G_Pressed = false;
            case H -> H_Pressed = false;
            case J -> J_Pressed = false;
            case K -> K_Pressed = false;
            case L -> L_Pressed = false;
            case Q -> Q_Pressed = false;
            case E -> E_Pressed = false;
            case R -> R_Pressed = false;
            case T -> T_Pressed = false;
            case Y -> Y_Pressed = false;
            case U -> U_Pressed = false;
            case I -> I_Pressed = false;
            case O -> O_Pressed = false;
            case P -> P_Pressed = false;
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
