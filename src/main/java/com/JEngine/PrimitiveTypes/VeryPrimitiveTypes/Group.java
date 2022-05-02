package com.JEngine.PrimitiveTypes.VeryPrimitiveTypes;

import javafx.scene.Node;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

public class Group extends javafx.scene.Group implements Serializable {
    public Group() {
        super();
    }

    public Group(Node... nodes) {
        super(nodes);
    }

    public Group(Collection<Node> collection) {
        super(collection);
    }
}
