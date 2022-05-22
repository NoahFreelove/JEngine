package com.JEngine.Components.UI;

import com.JEngine.Core.Component;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GenericMethod;
import javafx.scene.Node;

public class UIAnimator extends Component {
    private Vector2 startPos;
    private Vector2 endPos;
    private Vector2 startScale;
    private Vector2 endScale;

    private Node node;

    private GenericMethod onComplete;

    private float progress;
    private float timeTarget = 1;
    private boolean isPlaying;
    public UIAnimator(Vector2 startPos, Vector2 endPos, Node node, float timeTarget) {
        super("animator");
        this.startPos = startPos;
        this.endPos = endPos;
        this.node = node;
        this.timeTarget = timeTarget;
        this.startScale = new Vector2(node.getScaleX(), node.getScaleY());
        this.endScale = new Vector2(node.getScaleX(), node.getScaleY());
    }
    public UIAnimator(Vector2 startPos, Vector2 endPos, Node node, float timeTarget, GenericMethod onComplete) {
        super("animator");
        this.startPos = startPos;
        this.endPos = endPos;
        this.node = node;
        this.timeTarget = timeTarget;
        this.startScale = new Vector2(node.getScaleX(), node.getScaleY());
        this.endScale = new Vector2(node.getScaleX(), node.getScaleY());
        this.onComplete = onComplete;
    }

    public UIAnimator(Vector2 startPos, Vector2 endPos, Vector2 startScale, Vector2 endScale, Node node, float timeTarget) {
        super("animator");
        this.startPos = startPos;
        this.endPos = endPos;
        this.startScale = startScale;
        this.endScale = endScale;
        this.node = node;
        this.timeTarget = timeTarget;
    }
    public UIAnimator(Vector2 startPos, Vector2 endPos, Vector2 startScale, Vector2 endScale, Node node, float timeTarget, GenericMethod onComplete) {
        super("animator");
        this.startPos = startPos;
        this.endPos = endPos;
        this.startScale = startScale;
        this.endScale = endScale;
        this.node = node;
        this.timeTarget = timeTarget;
        this.onComplete = onComplete;
    }

    public void play(){
        isPlaying = true;
    }

    public void pause(){
        isPlaying = false;
    }

    public void stop(){
        pause();
        progress = 0;
    }

    public void restart(){
        stop();
        play();
    }
    @Override
    public void Update(){
        if(isPlaying)
        {
            if(progress <1)
            {
                Vector2 newPos = new Vector2(GameMath.interpolateClamped(new Vector3(startPos), new Vector3(endPos), progress));
                Vector2 newScale = new Vector2(GameMath.interpolateClamped(new Vector3(startScale), new Vector3(endScale), progress));
                node.setTranslateX(newPos.x);
                node.setTranslateY(newPos.y);
                node.setScaleX(newScale.x);
                node.setScaleY(newScale.y);
                progress += 1/GameWindow.getInstance().getTargetFPS()/timeTarget;
                if(progress>=1)
                {
                    if(onComplete !=null)
                        onComplete.call(null);
                    stop();
                }
            }
        }
    }
}
