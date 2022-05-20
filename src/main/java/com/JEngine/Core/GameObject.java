package com.JEngine.Core;

import com.JEngine.Components.Colliders.Collider_Comp;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Pawn;

import java.util.Arrays;

/** JObject (c) Noah Freelove
 * Brief Explanation:
 * A object is a simple class that has a transform value and is a primitive base for (nearly) everything that exists.
 *
 * Usage:
 * It is not recommended building off the object base class, but to extend off the Player or Pawn classes.
 * An object cannot be controlled directly, but it's position can be updated by doing Object.getTransform().setTransform
 * **/

public class GameObject extends Thing {
    private Transform transform;
    private Vector3 prevPos;
    private Component[] components = new Component[0];
    private GameObject[] children = new GameObject[0];
    private Collider_Comp[] colliders = new Collider_Comp[0];
    private final Identity identity;
    private boolean queuedForDeletion;

    public GameObject(Transform transform, Identity identity) {
        super(true);
        this.transform = transform;
        this.prevPos = transform.getPosition();
        this.identity = identity;
    }

    public GameObject(Vector2 pos) {
        super(true);
        this.transform = Transform.exSimpleTransform(pos.x, pos.y);
        this.prevPos = transform.getPosition();
        this.identity = new Identity("GameObject","gameobject");
    }
    public GameObject(Transform transform, Identity identity, boolean isActive) {
        super(isActive);
        this.transform = transform;
        this.prevPos = transform.getPosition();
        this.identity = identity;
    }

    // Called upon object's creation
    public void Start(){}

    // Called once every frame
    public void Update(){
        for(GameObject child : children) {
            if(child instanceof Pawn p)
            {
                // convert vector3 to direction
                Vector3 delta = prevPos.subtract(getPosition());
                p.Move(new Vector2(delta.x*-1, delta.y*-1), (int)Vector3.getMaxValue(delta));
                p.setScale(getTransform().scale);
            }
            child.Update();
        }
        for(Component c : components)
        {
            c.Update();
        }
        prevPos = getPosition();
    }

    public void OnDestroy(){}
    public void OnUnload(){}
    public void OnAdded(){}

    // Getters
    public Identity getIdentity() {
        return identity;
    }
    public Transform getTransform() {
        return transform;
    }
    public Vector3 getPosition(){
        return transform.getPosition();
    }
    public void setPosition(Vector3 position) {
        transform.setPosition(position);
    }
    public boolean isQueuedForDeletion() {
        return queuedForDeletion;
    }
    public GameObject[] getChildren() {
        return children;
    }
    public Component[] getComponents() {
        return components;
    }
    public Component[] getComponentByName(String name) {
        Component[] result = new Component[0];
        for(Component c : components) {
            if(c.getName().equals(name)) {
                Component[] newResult = new Component[result.length + 1];
                System.arraycopy(result, 0, newResult, 0, result.length);
                newResult[result.length] = c;
                result = newResult;
            }
        }
        return result;
    }
    public Component[] getComponentByType(Class<? extends Component> type) {
        Component[] result = new Component[0];
        for(Component c : components) {
            if(c.getClass().equals(type)) {
                Component[] newResult = new Component[result.length + 1];
                System.arraycopy(result, 0, newResult, 0, result.length);
                newResult[result.length] = c;
                result = newResult;
            }
        }
        return result;
    }

    public Collider_Comp[] getColliders() {
        return colliders;
    }

    // Setters
    public void setQueuedForDeletion(boolean queuedForDeletion) {
        this.queuedForDeletion = queuedForDeletion;
    }
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void addChild(GameObject child) {
        GameObject[] newChildren = new GameObject[children.length + 1];
        System.arraycopy(children, 0, newChildren, 0, children.length);
        newChildren[children.length] = child;
        children = newChildren;
    }
    public void removeChild(GameObject child) {
        GameObject[] newChildren = new GameObject[children.length - 1];
        int index = 0;
        for(GameObject g : children) {
            if(g != child)
                newChildren[index++] = g;
        }
        children = newChildren;
    }
    public void addComponent(Component component) {
        Component[] newComponents = new Component[components.length + 1];
        System.arraycopy(components, 0, newComponents, 0, components.length);
        component.setParent(this);
        newComponents[components.length] = component;
        components = newComponents;
        if(component instanceof Collider_Comp) {
            addCollider((Collider_Comp)component);
        }
    }
    public void addComponents(Component... components) {
        for(Component c : components) {
            addComponent(c);
        }
    }

    public void addCollider(Collider_Comp collider) {
        Collider_Comp[] newColliders = new Collider_Comp[colliders.length + 1];
        System.arraycopy(colliders, 0, newColliders, 0, colliders.length);
        collider.setParent(this);
        newColliders[colliders.length] = collider;
        colliders = newColliders;
    }
    public void removeComponent(Component component) {
        Component[] newComponents = new Component[components.length - 1];
        int index = 0;
        for(Component c : components) {
            if(c != component)
                newComponents[index++] = c;
        }
        components = newComponents;
    }
    @Override
    public String toString() {
        return "GameObject{" +
                "identity=" + identity +
                ", transform=" + transform +
                ", children=" + Arrays.toString(children) +
                ", components=" + Arrays.toString(components) +
                '}';
    }

    public void setScale(Vector3 newScale)
    {
        getTransform().setScale(newScale);
    }
}
