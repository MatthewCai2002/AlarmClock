package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Siren {
    private static final String FILE_PATH = "./data/Sirens/Chinese-EAS-Alarm.wav";

    private AudioInputStream audioInputStream;
    private Clip clip;


    public Siren() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(FILE_PATH));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void playAudio() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopAudio() {
        clip.stop();
    }

    /*public static void main(String[] args) {
        try {
            Siren siren = new Siren();
            siren.playAudio();
            while (true) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }*/
}
