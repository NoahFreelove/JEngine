package com.JEngine.Game.Visual.Animation;

import com.JEngine.PrimitiveTypes.JImage;
import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

/** JAnimationTimeline (c) Noah Freelove
 * Brief Explanation:
 * A timeline is a collection of frames that are played in order based on their AnimState.
 *
 * Create a timeline with multiple frames and call getNextFrame() in your sprite's update method to update the image
 * and skip ahead to the next frame
 * **/

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

    /**
     * Initializes the timeline with the given frames
     * @param frames 2d array of frames
     * @param initState the initial state of the timeline
     * @param maxFramesInAnim the maximum (total) number of frames in an animation state. this includes the duration of the frames.
     *                        ex: AnimFrame(duration 5) and AnimFrame(duration 3) would be a total of 8 frames
     */
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

        specialFrames = new JImage[maxFramesInAnim];
        int specialCount = 0;

        special2Frames = new JImage[maxFramesInAnim];
        int special2Count = 0;

        special3Frames = new JImage[maxFramesInAnim];
        int special3Count = 0;

        special4Frames = new JImage[maxFramesInAnim];
        int special4Count = 0;

        boolean invalidEntry = false;

        // check total frames in each arr adds up to maxFramesInAnim
        for (AnimFrame[] arr :
                frames) {
            int totalFrames = 0;
            for (AnimFrame frame :
                    arr) {
                if(frame == null)
                    continue;
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
        // init every frame array
        for (AnimFrame[] fArr :
                frames) {
            for (AnimFrame f:
                    fArr) {
                if(f == null)
                    continue;
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
                    case SPECIAL -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            specialFrames[specialCount] = f.image;
                            specialCount++;
                        }
                    }
                    case SPECIAL2 -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            special2Frames[special2Count] = f.image;
                            special2Count++;
                        }
                    }
                    case SPECIAL3 -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            special3Frames[special3Count] = f.image;
                            special3Count++;
                        }
                    }
                    case SPECIAL4 -> {
                        while (f.duration>0)
                        {
                            f.duration--;
                            special4Frames[special4Count] = f.image;
                            special4Count++;
                        }
                    }
                }
            }
        }
    }

    /**
     * switch the anim state
     * @param newState the new state
     */
    public void switchState(AnimState newState)
    {
        currState = newState;
    }

    /**
     * get the next frame of the current state
     * @return the next frame of the current state. Will return null JImage if there is no frame for the current state!
     */
    public JImage getNextFrame()
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
                case SPECIAL -> tmp = specialFrames[frameIndex];
                case SPECIAL2 -> tmp = special2Frames[frameIndex];
                case SPECIAL3 -> tmp = special3Frames[frameIndex];
                case SPECIAL4 -> tmp = special4Frames[frameIndex];
            }
            frameIndex++;
        }
        catch (Exception ignore)
        {
            frameIndex = 0;
            assert currState != null;
            return new JImage(null,64,64);
        }
        return tmp;
    }
}
