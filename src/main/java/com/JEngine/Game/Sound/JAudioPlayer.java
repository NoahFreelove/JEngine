package com.JEngine.Game.Sound;

import com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class JAudioPlayer extends Thing {
    private String filePath;
    private File wavFile;
    private Clip clip;

    public JAudioPlayer(String filepath) {
        super(true);
        this.filePath = filepath;
        openClip(filePath);
    }

    public void openClip(String filepath)
    {
        try{
            wavFile = new File(filepath);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(wavFile));
        }
        catch (Exception e)
        {
            LogError("Could not access audio file at: " + filepath);
        }
    }

    public void startClip()
    {
        clip.start();
        LogExtra("Started clip:" + filePath);
    }
    public void stopClip()
    {
        clip.stop();
        LogExtra("Stopped clip:" + filePath);
    }
    public void restartClip()
    {
        clip.flush();
        clip.start();
        LogExtra("Restarted clip:" + filePath);
    }

}
