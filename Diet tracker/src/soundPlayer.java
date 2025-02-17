import javax.sound.sampled.*;
import java.io.*;

public class soundPlayer {
    public static void playSound(String filePath) {
        try {
            // Load the sound file
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip(); // Get a sound clip resource

            clip.open(audioStream); // Open the clip and load samples from the audio input stream

            clip.start(); // Start the clip

            clip.addLineListener(event -> { // Keep the program running until the sound is finished
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}