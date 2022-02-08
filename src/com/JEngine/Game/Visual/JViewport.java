package com.JEngine.Game.Visual;

// based on camera location holds list of objects that can be in view
// requires JScene
public class JViewport {
    public JScene scene;

    public JViewport(JScene scene) {
        this.scene = scene;
    }
}
