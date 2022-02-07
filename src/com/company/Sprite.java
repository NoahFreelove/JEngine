package com.company;

import java.awt.*;

/** JEngine.Sprite (c) Noah Freelove
 * A sprite is a object with an image
 * A object cannot be controlled by the player, but it's position can be updated
 * **/
public class Sprite {
    Object object;
    Image sprite;

    public void setSprite(Image newSprite) {sprite = newSprite;}
    public void migrateObject(Object newObject) {object = newObject;}
}
