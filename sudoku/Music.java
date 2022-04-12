package sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Music {
    File file;
    public Music() {
        file = new File(getClass().getResource("Music.wav").getPath());

        

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

    public void playMusic(){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured when loading background music", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}