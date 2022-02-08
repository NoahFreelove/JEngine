package com.JEngine.Game.Visual;

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
}
