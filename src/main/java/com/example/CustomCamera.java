package com.example;

import com.JEngine.Game.Visual.JCamera;
import com.JEngine.Game.Visual.Scenes.JScene;
import com.JEngine.Game.Visual.JWindow;
import com.JEngine.PrimitiveTypes.Position.Vector3;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JObject;

public class CustomCamera extends JCamera {
    public CustomCamera(Vector3 position, JWindow window, JScene scene, JObject parent, com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity JIdentity) {
        super(position, window, scene, parent, JIdentity);
    }

    @Override
    public void Update()
    {
        //transform.setPosition(new Vector3(transform.getPosition().x-10,transform.getPosition().y,0));
    }
}
