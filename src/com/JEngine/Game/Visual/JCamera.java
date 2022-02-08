package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Object;

import javax.swing.*;
import java.awt.*;

// Convert objects in the viewport to a visual representation
// Requires JViewport
public class JCamera extends Object {
    public float fov;
    public JScene scene;
    public JWindow window;
    public Object parent;
    public Object[] objectsInView;
    public JCamera(JWindow window, JScene scene, Object parent, float fov) {
        super(parent.transform);
        this.window = window;
        this.scene = scene;
        this.parent = parent;
        this.fov = fov;
    }

    public void InitiateRender()
    {
        System.out.println("Initiate Render");
        GetObjectsInView();
    }

    private void GetObjectsInView()
    {
        super.transform.position = parent.transform.position;
        Render();
    }

    private void Render()
    {
        JPanel panel = (JPanel) window.frame.getContentPane();
        JLabel jl = new JLabel(new JImage(true, "bin\\\\image.png").getImage());
        panel.add(jl);
        Dimension size = jl.getPreferredSize();
        jl.setBounds(100, 100, size.width, size.height);
        window.refreshWindow(panel);
    }
}
