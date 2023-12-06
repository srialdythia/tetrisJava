package tetrisjava;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    private String folderName = "sounds" + File.separator;
    private String tPath, clPath, luPath, goPath;
    private Clip tSound, clSound, luSound, goSound;
    
    public AudioPlayer(){
        tPath = folderName + "tetris.wav";
        clPath = folderName + "clear-line.wav";
        luPath = folderName + "next-level.wav";
        goPath = folderName + "game-over.wav";

        try {
            tSound = AudioSystem.getClip();
            clSound = AudioSystem.getClip();
            luSound = AudioSystem.getClip();
            goSound = AudioSystem.getClip();
            
            tSound.open(AudioSystem.getAudioInputStream(new File(tPath).getAbsoluteFile()));
            clSound.open(AudioSystem.getAudioInputStream(new File(clPath).getAbsoluteFile()));
            luSound.open(AudioSystem.getAudioInputStream(new File(luPath).getAbsoluteFile()));
            goSound.open(AudioSystem.getAudioInputStream(new File(goPath).getAbsoluteFile()));

        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void startTetrisSound(){
        tSound.setFramePosition(0);
        tSound.loop(Clip.LOOP_CONTINUOUSLY);
        tSound.start();
    }
    public void stopTetrisSound(){
        tSound.stop();
    }
    public void startClearLineSound(){
        clSound.setFramePosition(0);
        clSound.start();
    }
    public void startLevelUpSound(){
        luSound.setFramePosition(0);        
        luSound.start();
    }
    public void startGameOverSound(){
        goSound.setFramePosition(0);
        goSound.start();
    }    
}
