package sudoku;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TimeManagement {
    public static int secondPassed = 0;
    public static Timer timer = new Timer();
    public static JLabel timerDisplay = new JLabel("00:00", SwingConstants.CENTER);

    public static TimerTask task = new TimerTask() {
        public void run() {
            secondPassed++;
            timerDisplay.setText(String.format("%02d:%02d", secondPassed / 60, secondPassed % 60));
        }
    };

    public static void startTimer() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public static void incrementTime() {
        secondPassed++;
    }

    public static int getTime() {
        return secondPassed;
    }

    public static void stopTimer() {
        // timerDisplay.setForeground(Color.BLUE);
        timer.cancel();
        timer.purge();
    }

    public static void resetTimer() {
        timer.cancel();
        timer.purge();
        secondPassed = 0;
        timer = new Timer();
        timerDisplay.setText("00:00");
        task = new TimerTask() {
            public void run() {
                secondPassed++;
                timerDisplay.setText(String.format("%02d:%02d", secondPassed / 60, secondPassed % 60));
            }
        };
    }
}

// private long secPassed;
// private long timeMillis;

// public TimeManagement() {
// secPassed = 0;
// }

// public void run() {
// timeMillis = System.currentTimeMillis();
// secPassed = timeMillis / 1000;
// }

// public void setTime(int secPassed) {
// this.secPassed = secPassed;
// }

// public long getTime() {
// return secPassed;
// }
