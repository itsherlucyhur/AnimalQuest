import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The SoundPlayer class represents an audio that will be played in
 *
 * @author Sehee Ryoo
 * @version 19.0.2
 */
public class SoundPlayer {

    // Declaring Clip as a class variable
    private static Clip clip;

    /**
     * Method to play the sound given the path to the file name
     *
     * @param filePath path to the audio file in String
     */
    public static void playSound(String filePath) {
        File soundFile = new File(filePath);

        try {
            // Create an AudioInputStream object and get a Clip object
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();

            // Open audio clip and load from the audio input stream
            clip.open(audioIn);

            // Check which sound is playing: for background music, play the sound and loop indefinitely
            if (filePath.equals("animalquestmusic.wav")) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                // For other sounds, just play once
                clip.start();
            }

            // Catch any exception
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to stop the sound that is currently playing
     */
    public static void stopSound() {
        // If audio is running, stop the clip object
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}