package com.JEngine.Game.Visual.Animation;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

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
        this.currState = initState;
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
        boolean invalidEntry = false;

        for (AnimFrame[] arr :
                frames) {
            int totalFrames = 0;
            for (AnimFrame frame :
                    arr) {
                totalFrames+=frame.duration;
                if (maxFramesInAnim < totalFrames) {
                    invalidEntry = true;
                    break;
                }
            }
        }
        if(invalidEntry) {
            Thing.LogError("Invalid entry of maxFramesInAnim for an animation timer");
            return;
        }

        for (AnimFrame[] fArr :
                frames) {
            for (AnimFrame f:
                    fArr) {
                switch (f.state)
                {
                    case IDLE -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            idleFrames[idleCount] = f.image;
                            idleCount++;
                        }
                    }
                    case LEFT -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            leftFrames[leftCount] = f.image;
                            leftCount++;
                        }
                    }
                    case RIGHT -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            rightFrames[rightCount] = f.image;
                            rightCount++;
                        }
                    }
                    case UP -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            upFrames[upCount] = f.image;
                            upCount++;
                        }
                    }
                    case DOWN -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            downFrames[downCount] = f.image;
                            downCount++;
                        }
                    }
                }
            }
        }

    }
    public void switchState(AnimState newState)
    {
        currState = newState;
    }

    public JImage getCurrentFrame()
    {
        JImage tmp = null;
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
            assert currState != null;
            return switch (currState)
                    {
                        case IDLE -> idleFrames[0];
                        case UP -> upFrames[0];
                        case DOWN -> downFrames[0];
                        case LEFT -> leftFrames[0];
                        case RIGHT -> rightFrames[0];
                        default -> idleFrames[0];
                    };
        }
        return tmp;
    }
}
