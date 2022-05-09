package com.JEngine.Game.Sound;

import com.JEngine.Core.Component;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.GameMath;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/** AudioPlayer (c) Noah Freelove
 * Brief Explanation:
 * A simple way to play audio files.
 * Not advanced yet, planning to add more functionality.
 * **/
public class AudioPlayer extends Component {
    private final String filePath;
    private Clip clip;
    private boolean playInWorldSpace = true;
    private float volume = 1f;
    private FloatControl gainControl;

    public AudioPlayer(String filepath) {
        super(true, "AudioPlayer");
        this.filePath = filepath;
        setClip(filePath);
        gainControl  = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }

    @Override
    public void Update(){
        if(playInWorldSpace)
        {
            Vector3 distanceFromCamera = SceneManager.getActiveCamera().getPosition();
            Vector3 soundPosition = getParent().getPosition();
            Vector3 newPosition = soundPosition.subtract(distanceFromCamera);
            float totalDistance = newPosition.x + newPosition.y;
            setVolume(5-(Math.abs(totalDistance)/100));

        }
        gainControl.setValue(volume*5);
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

    public void start()
    {
        clip.start();
        LogExtra("Started clip:" + filePath);
    }
    public void pause()
    {
        clip.stop();
        LogExtra("Started clip:" + filePath);
    }


    /**
     * Stop the audio clip
     */
    public void stopClip()
    {
        clip.stop();
        clip.flush();
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

    public AudioInputStream getAudioInputStream() {
        return null;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = GameMath.clamp(-16f, 1f, volume);
    }
}
