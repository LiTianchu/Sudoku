package sudoku;

import java.io.File;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Music {
    static File file;
    static File congratFile;
    static Clip clip;
    static Clip congratClip;
    static long clipTimePosition;

    public Music() {
        file = new File(getClass().getResource("Music.wav").getPath());
        congratFile = new File(getClass().getResource("Congrat.wav").getPath());

    }

    public void playMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            if (clipTimePosition > 0) {
                clip.setMicrosecondPosition(clipTimePosition);
            }

            clip.start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured when loading background music", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void stopMusic() {
        clipTimePosition = clip.getMicrosecondPosition();
        System.out.println("cliptimeposition: " + clipTimePosition);
        clip.stop();
    }

    public void playCongratMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(congratFile);
            congratClip = AudioSystem.getClip();
            congratClip.open(audioInputStream);
            congratClip.start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured when loading congrat music", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}