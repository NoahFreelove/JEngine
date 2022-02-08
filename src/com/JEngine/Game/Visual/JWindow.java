package com.JEngine.Game.Visual;

import javax.swing.*;

/** JEngine.JWindow (c) Noah Freelove
 * Brief Explanation:
 * A JWindow is an extension from javax.swing
 *
 * Usage:
 * A JWindow can be used to create a window. A window is needed to create a scene.
 * **/

public class JWindow {
    private JFrame frame;

    public JWindow(int sizeX, int sizeY, String title, boolean defaultVisibilityState) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setTitle(title);
        frame.setVisible(defaultVisibilityState);
    }

    public JFrame getWindow() {return frame;}
    public void setWindow(JFrame newFrame) {frame = newFrame;}
    public void setVisibility(boolean newVisibility) {frame.setVisible(newVisibility);}
}
