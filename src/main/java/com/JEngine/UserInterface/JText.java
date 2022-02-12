package com.JEngine.UserInterface;

import com.JEngine.PrimitiveTypes.Position.Transform;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;

public class JText extends JUIObject {
    Text label;
    public JText(Transform transform, JIdentity JIdentity, String text, Font font, int sizeX, int sizeY) {
        super(transform, JIdentity, sizeX, sizeY);
        label = new Text(text);
    }

    public void setText(String newText){label.setText(newText);}
    //public void setFont(Font newFont){label.setFont(newFont);}
    //public void setColor(Color newColor){label}
    public String getText() {return label.getText();}
    public Text getLabel(){return label;}
}
