package com.JEngine.UserInterface;

import com.JEngine.PrimitiveTypes.Position.Transform;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JUIObject;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.JIdentity;

import javax.swing.*;
import java.awt.*;

public class JText extends JUIObject {
    JLabel label = new JLabel();
    public JText(Transform transform, JIdentity JIdentity, String text, Font font) {
        super(transform, JIdentity);
        label.setText(text);
        label.setFont(font);
        label.setForeground(Color.BLACK);
    }

    public void setText(String newText){label.setText(newText);}
    public void setFont(Font newFont){label.setFont(newFont);}
    public String getText() {return label.getText();}
    public JLabel getLabel(){return label;}
}