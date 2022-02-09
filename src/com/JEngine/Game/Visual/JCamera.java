package com.JEngine.Game.Visual;

import com.JEngine.Game.PlayersAndPawns.Pawn;
import com.JEngine.Game.PlayersAndPawns.Sprite;
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
        GetObjectsInView();
    }

    private void GetObjectsInView()
    {
        super.transform.position = parent.transform.position;
        Render();
    }

    private void Render()
    {
        JPanel panel = (JPanel) window.getWindow().getContentPane();
        panel.removeAll();
        for (int i = 0; i < scene.sceneObjects.length; i++) {

            if(scene.sceneObjects[i] == null)
            {
                continue;
            }

            JLabel jl = new JLabel((scene.sceneObjects[i].objRef.getSprite().getImage()));
            Dimension size = jl.getPreferredSize();
            jl.setBounds((int)scene.sceneObjects[i].objRef.transform.position.x, (int)scene.sceneObjects[i].objRef.transform.position.y, size.width, size.height);

            panel.add(jl);
        }
        LogExtra("Rendered Objects");
        window.refreshWindow(panel);
    }
}
