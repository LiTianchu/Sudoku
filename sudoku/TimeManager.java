package sudoku;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TimeManager {
    static int secondPassed = 86396;
    static Timer timer = new Timer();
    static JLabel timerDisplay = new JLabel("00:00:00", SwingConstants.CENTER);

    static TimerTask task = new TimerTask() {
        public void run() {
            secondPassed++;
            if (secondPassed / 60 / 60 % 60 >= 24) {
                secondPassed = 0;
            }
            timerDisplay.setText(String.format("%02d:%02d:%02d", secondPassed / 60 / 60 % 60, secondPassed / 60 % 60,
                    secondPassed % 60));
        }
    };

    static void startTimer() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    static void incrementTime() {
        secondPassed++;
    }

    static int getTime() {
        return secondPassed;
    }

    static void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    static void resetTimer() {
        stopTimer();
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
