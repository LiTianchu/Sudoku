package sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Music {
    static File file;
    static File congratFile;
    static Clip clip;
    static Clip congratClip;
    static long clipTimePosition;

    public Music() {
        file = new File("sudoku/Music.wav");
        congratFile = new File("sudoku/Congrat.wav");

        // AudioPlayer MGP = AudioPlayer.player;
        // AudioStream BGM;
        // AudioData MD;
        // ContinuousAudioDataStream loop = null;

        // try {
        // BGM = new AudioStream(new FileInputStream(MUSIC_PATH));
        // System.out.println("bmg:" + BGM);
        // System.out.println(MUSIC_PATH);

        // MD = BGM.getData();
        // loop = new ContinuousAudioDataStream(MD);

        // } catch (IOException e) {
        // System.out.println("eror");
        // }

        // MGP.start(loop);
        // }
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