package com.JEngine.Core;

import javafx.scene.Node;

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
