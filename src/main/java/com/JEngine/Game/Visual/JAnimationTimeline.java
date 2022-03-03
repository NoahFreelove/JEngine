package com.JEngine.Game.Visual;

import com.JEngine.PrimitiveTypes.JImage;

enum AnimState {
    IDLE,
    LEFT,
    RIGHT,
    UP,
    DOWN,
    SPECIAL,
    SPECIAL2,
    SPECIAL3,
    SPECIAL4
}
class AnimFrame{
    AnimState state;
    JImage image;
    int duration; // in frames
}
public class JAnimationTimeline {
    public AnimState currState;
    int frameIndex;

    public JImage[] idleFrames;
    public JImage[] leftFrames;
    public JImage[] rightFrames;
    public JImage[] downFrames;
    public JImage[] upFrames;
    public JImage[] specialFrames;
    public JImage[] special2Frames;
    public JImage[] special3Frames;
    public JImage[] special4Frames;

    public JAnimationTimeline(AnimFrame[][] frames, AnimState initState, int maxFramesInAnim) {
        // if a frame is null, it will be skipped. maxFramesInAnim is just to not make a huge array
        idleFrames = new JImage[maxFramesInAnim];
        int idleCount = 0;

        leftFrames = new JImage[maxFramesInAnim];
        int leftCount = 0;

        rightFrames = new JImage[maxFramesInAnim];
        int rightCount = 0;

        upFrames = new JImage[maxFramesInAnim];
        int upCount = 0;

        downFrames = new JImage[maxFramesInAnim];
        int downCount = 0;

        for (AnimFrame[] fArr :
                frames) {
            for (AnimFrame f:
                    fArr) {
                switch (f.state)
                {
                    case IDLE -> {
                        while (f.duration>0)
                        {
                            idleFrames[idleCount] = f.image;
                            idleCount++;
                        }
                    }
                    case LEFT -> {
                        while (f.duration>0)
                        {
                            leftFrames[leftCount] = f.image;
                            leftCount++;
                        }
                    }
                    case RIGHT -> {
                        while (f.duration>0)
                        {
                            rightFrames[rightCount] = f.image;
                            rightCount++;
                        }
                    }
                    case UP -> {
                        while (f.duration>0)
                        {
                            upFrames[upCount] = f.image;
                            upCount++;
                        }
                    }
                    case DOWN -> {
                        while (f.duration>0)
                        {
                            downFrames[downCount] = f.image;
                            downCount++;
                        }
                    }
                }
            }
        }

        this.currState = initState;
    }
    public void switchState(AnimState newState)
    {
        currState = newState;
    }

    public JImage getCurrentFrame()
    {
        JImage tmp = new JImage(false,"",0,0);
        try {
            switch (currState)
            {
                case IDLE -> tmp = idleFrames[frameIndex];
                case UP -> tmp = upFrames[frameIndex];
                case DOWN -> tmp = downFrames[frameIndex];
                case LEFT -> tmp = leftFrames[frameIndex];
                case RIGHT -> tmp = rightFrames[frameIndex];
            }
            frameIndex++;
        }
        catch (Exception ignore)
        {
            frameIndex = 0;
            return getCurrentFrame();
        }
        return tmp;
    }
}
