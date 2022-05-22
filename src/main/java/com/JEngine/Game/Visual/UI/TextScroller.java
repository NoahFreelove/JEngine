package com.JEngine.Game.Visual.UI;

import com.JEngine.Core.Component;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GenericMethod;
import javafx.scene.text.Text;

public class TextScroller extends Component {

    private Text animText;
    private String content;
    private int index = 0;
    private float timeTarget;
    private float progress = 0f;
    private GenericMethod onComplete;
    private boolean isPlaying;

    public TextScroller(String content, Text text, float time, GenericMethod onComplete) {
        super("textScroller");
        this.animText = text;
        this.timeTarget = time;
        this.content = content;
        this.onComplete = onComplete;
        this.progress = 0f;
    }
    public TextScroller(String content, Text text, float time) {
        super("textScroller");
        this.animText = text;
        this.timeTarget = time;
        this.content = content;
        this.onComplete = null;
        this.progress = 0f;
    }

    public void play(){
        isPlaying = true;
    }

    public void pause(){
        isPlaying = false;
    }

    public void stop(){
        pause();
        animText.setText("");
        progress = 0;
    }

    public void restart(){
        stop();
        play();
    }
    public void skip(){
        progress = 1f;
        if(onComplete != null)
            onComplete.call(null);
    }

    @Override
    public void Update(){
        if(isPlaying)
        {
            if(progress < 1)
            {
                progress += 1/ GameWindow.getInstance().getTargetFPS()/timeTarget;
                index = (int) (progress * content.length());
                animText.setText(content.substring(0, GameMath.clamp( 0, content.length(), index)));
                if(progress >= 1)
                {
                    pause();
                    if(onComplete !=null)
                    {
                        onComplete.call(null);
                    }
                }
            }
        }
    }

    public float getProgress() {
        return progress;
    }
}
