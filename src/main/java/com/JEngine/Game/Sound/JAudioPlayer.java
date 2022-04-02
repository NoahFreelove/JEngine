package com.JEngine.Game.Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogError;
import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogExtra;

/** JAudioPlayer (c) Noah Freelove
 * Brief Explanation:
 * A simple way to play audio files.
 * Not advanced yet, planning to add more functionality.
 * **/
public class JAudioPlayer {
    private final String filePath;
    private Clip clip;

    public JAudioPlayer(String filepath) {
        this.filePath = filepath;
        setClip(filePath);
    }

    /**
     * Set the audio clip from filepath
     * @param filepath The filepath of the audio file
     */
    public void setClip(String filepath)
    {
        try{
            File wavFile = new File(filepath);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(wavFile));
        }
        catch (Exception e)
        {
            LogError("Could not access audio file at: " + filepath);
        }
    }

    /**
     * Play the audio clip
     */
    public void startClip()
    {
        clip.start();
        LogExtra("Started clip:" + filePath);
    }

    /**
     * Stop the audio clip
     */
    public void stopClip()
    {
        clip.stop();
        LogExtra("Stopped clip:" + filePath);
    }

    /**
     * Restart the clip
     */
    public void restartClip()
    {
        clip.flush();
        clip.start();
        LogExtra("Restarted clip:" + filePath);
    }

}
