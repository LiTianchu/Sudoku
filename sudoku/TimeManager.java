package sudoku;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TimeManager {
    static int secondPassed = 0;
    static int secondPassedForShow = 0;
    static Timer timer = new Timer();
    static JLabel timerDisplay = new JLabel("00:00:00", SwingConstants.CENTER);

    static TimerTask task;

   

    static void startTimer() {
        // set timer task
        task = new TimerTask() {
            public void run() {
                secondPassed++;
                secondPassedForShow++;
                if (secondPassed / 60 / 60>= 100) { // when reach 99:59:59, reset to 0
                    secondPassed = 0;
                }
                timerDisplay.setText(String.format("%02d:%02d:%02d", secondPassed / 60 / 60, secondPassed / 60 % 60,
                        secondPassed % 60));// display the time
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    static void incrementTime() {
        secondPassed++;
    }

    static int getTime() {
        return secondPassedForShow;
    }

    static void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    static void resetTimer() {
        stopTimer();
        secondPassed = 0;
        secondPassedForShow=0;
        timer = new Timer();
        timerDisplay.setText("00:00:00");
    }
}
