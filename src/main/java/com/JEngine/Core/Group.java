package com.JEngine.Core;

import javafx.scene.Node;

import java.util.Collection;

public class Group extends javafx.scene.Group  {
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
