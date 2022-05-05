package com.JEngine.Utility;

import com.JEngine.Game.PlayersAndPawns.Player;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.PrimitiveTypes.GameObject;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Input (c) Noah Freelove
 * When <key>_Pressed = true its, pressed/enabled
 * When <key>_Released = false its, released/disabled
 */
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

    /**
     * Set window key handler events
     * @param scene JavaFX scene to add handlers to
     */
    public static void init(Scene scene) {

        scene.addEventHandler(KeyEvent.KEY_PRESSED, Input::keyPressEvent);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, Input::keyReleaseEvent);

    }

    /**
     * When a key is pressed, set the key to enabled
     * @param key Key pressed
     */
    private static void keyPressEvent(KeyEvent key)
    {
        for (GameObject o :
                SceneManager.getActiveScene().getObjects()) {
            try
            {
                ((Player)o).onKeyPressed(key.getCode());
            }
            catch (Exception ignore){}
        }
        setKeys(key.getCode(), true);
    }

    /**
     * When a key is released, set the key to disabled
     * @param key Key released
     */
    private static void keyReleaseEvent(KeyEvent key)
    {
        for (GameObject o :
                SceneManager.getActiveScene().getObjects()) {
            try
            {
                ((Player)o).onKeyReleased(key.getCode());
            }
            catch (Exception ignore){}
        }
        setKeys(key.getCode(), false);
    }

    /**
     * Check if certain keys are pressed, so you can have multiple keys corresponding to one action
     */
    static void checkKeyCombos()
    {
        Up = W_Pressed||UArrow_Pressed;
        Down = S_Pressed||DArrow_Pressed;
        Left = A_Pressed||LArrow_Pressed;
        Right = D_Pressed||RArrow_Pressed;
    }

    // Set all keys to not pressed
    public static void resetKeys()
    {
        W_Pressed = false;
        A_Pressed = false;
        S_Pressed = false;
        D_Pressed = false;
        LArrow_Pressed = false;
        UArrow_Pressed = false;
        DArrow_Pressed = false;
        RArrow_Pressed = false;
        Shift_Pressed = false;
        Control_Pressed = false;
        Alt_Pressed = false;
        Tab_Pressed = false;
        NumRow0_Pressed = false;
        NumRow1_Pressed = false;
        NumRow2_Pressed = false;
        NumRow3_Pressed = false;
        NumRow4_Pressed = false;
        NumRow5_Pressed = false;
        NumRow6_Pressed = false;
        NumRow7_Pressed = false;
        NumRow8_Pressed = false;
        NumRow9_Pressed = false;
        Z_Pressed = false;
        X_Pressed = false;
        C_Pressed = false;
        V_Pressed = false;
        B_Pressed = false;
        N_Pressed = false;
        M_Pressed = false;
        F_Pressed = false;
        G_Pressed = false;
        H_Pressed = false;
        J_Pressed = false;
        K_Pressed = false;
        L_Pressed = false;
        Q_Pressed = false;
        E_Pressed = false;
        R_Pressed = false;
        T_Pressed = false;
        Y_Pressed = false;
        U_Pressed = false;
        I_Pressed = false;
        O_Pressed = false;
        P_Pressed = false;
        checkKeyCombos();
    }

    /**
     * Set a specific key state
     * @param key key to affect
     * @param state state to set
     */
    static void setKeys(KeyCode key, boolean state) {
        switch (key)
        {
            case W -> W_Pressed = state;
            case A -> A_Pressed = state;
            case S -> S_Pressed = state;
            case D -> D_Pressed = state;
            case LEFT -> LArrow_Pressed = state;
            case UP -> UArrow_Pressed = state;
            case DOWN -> DArrow_Pressed = state;
            case RIGHT -> RArrow_Pressed = state;
            case SHIFT -> Shift_Pressed = state;
            case CONTROL -> Control_Pressed = state;
            case ALT -> Alt_Pressed = state;
            case TAB -> Tab_Pressed = state;
            case DIGIT0 -> NumRow0_Pressed = state;
            case DIGIT1 -> NumRow1_Pressed = state;
            case DIGIT2 -> NumRow2_Pressed = state;
            case DIGIT3 -> NumRow3_Pressed = state;
            case DIGIT4 -> NumRow4_Pressed = state;
            case DIGIT5 -> NumRow5_Pressed = state;
            case DIGIT6 -> NumRow6_Pressed = state;
            case DIGIT7 -> NumRow7_Pressed = state;
            case DIGIT8 -> NumRow8_Pressed = state;
            case DIGIT9 -> NumRow9_Pressed = state;
            case Z -> Z_Pressed = state;
            case X -> X_Pressed = state;
            case C -> C_Pressed = state;
            case V -> V_Pressed = state;
            case B -> B_Pressed = state;
            case N -> N_Pressed = state;
            case M -> M_Pressed = state;
            case F -> F_Pressed = state;
            case G -> G_Pressed = state;
            case H -> H_Pressed = state;
            case J -> J_Pressed = state;
            case K -> K_Pressed = state;
            case L -> L_Pressed = state;
            case Q -> Q_Pressed = state;
            case E -> E_Pressed = state;
            case R -> R_Pressed = state;
            case T -> T_Pressed = state;
            case Y -> Y_Pressed = state;
            case U -> U_Pressed = state;
            case I -> I_Pressed = state;
            case O -> O_Pressed = state;
            case P -> P_Pressed = state;

        }
        checkKeyCombos();
    }

}
