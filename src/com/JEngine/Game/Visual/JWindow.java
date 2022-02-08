package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.JImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/** JEngine.JWindow (c) Noah Freelove
 * Brief Explanation:
 * A JWindow is an extension from javax.swing
 *
 * Usage:
 * A JWindow can be used to create a window. A window is needed to create a scene.
 * **/

public class JWindow {
    public JFrame frame;
    JPanel panel;
    public JWindow(int sizeX, int sizeY, String title, boolean defaultVisibilityState) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);

        frame.setSize(sizeX, sizeY);
        frame.setVisible(defaultVisibilityState);
    }

    public JFrame getWindow() {return frame;}
    public void setWindow(JFrame newFrame) {frame = newFrame;}
    public void setVisibility(boolean newVisibility) {frame.setVisible(newVisibility);}

    public void Refresh(JPanel newPanel)
    {
        newPanel.setLayout(null);
        panel = newPanel;
        System.out.println("Refreshed");
        frame.repaint();
    }
}
