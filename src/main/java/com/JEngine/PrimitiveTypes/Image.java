package com.JEngine.PrimitiveTypes;

import java.io.InputStream;
import java.io.Serializable;

public class Image extends javafx.scene.image.Image implements Serializable {

    public Image(String s) {
        super(s);
    }

    public Image(String s, boolean b) {
        super(s, b);
    }

    public Image(String s, double v, double v1, boolean b, boolean b1) {
        super(s, v, v1, b, b1);
    }

    public Image(String s, double v, double v1, boolean b, boolean b1, boolean b2) {
        super(s, v, v1, b, b1, b2);
    }

    public Image(InputStream inputStream) {
        super(inputStream);
    }

    public Image(InputStream inputStream, double v, double v1, boolean b, boolean b1) {
        super(inputStream, v, v1, b, b1);
    }
}
