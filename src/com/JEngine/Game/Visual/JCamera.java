package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.JImage;

import javax.swing.*;
import java.awt.*;

// Convert objects in the viewport to a visual representation
// Requires JViewport
public class JCamera {
    public JViewport viewport;
    public JScene scene;
    public JWindow window;

    public JCamera(JWindow window, JScene scene, JViewport viewport) {
        this.window = window;
        this.scene = scene;
        this.viewport = viewport;
    }

    public void Render()
    {
        JPanel panel = (JPanel) window.frame.getContentPane();
        JLabel jl = new JLabel(new JImage(true, "bin\\\\image.png").getImage());
        panel.add(jl);
        Dimension size = jl.getPreferredSize();
        jl.setBounds(100, 100, size.width, size.height);
        window.Refresh(panel);
    }
}
