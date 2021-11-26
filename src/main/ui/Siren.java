package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/*
    represents a siren with an audio clip and audio input stream
 */
public class Siren {
    private static final String FILE_PATH = "./data/Sirens/Chinese-EAS-Alarm.wav";

    private AudioInputStream audioInputStream;
    private Clip clip;

    // MODIFIES: this
    // EFFECTS: constructs a siren with an clip and audioInputStream
    public Siren() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(FILE_PATH));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    // EFFECTS: plays audio clip
    public void playAudio() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // EFFECTS: stops audio playback
    public void stopAudio() {
        clip.stop();
    }
}
