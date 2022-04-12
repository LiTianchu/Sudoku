package sudoku;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.net.URL;
//import java.sql.Time;
//import java.util.TimerTask;
import java.util.Random;
//import java.util.Timer;

import javax.sound.sampled.*;
import javax.swing.*;
//import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class SudokuMain extends JFrame {
    // private variables
    GameBoard board = new GameBoard();
    // Container cp = getContentPane(); // Container Sudoku
    JFrame cp = new JFrame("Suduko"); // Sudoku Menu
    JButton btnNewGame = new JButton("New Game");
    JButton btnShowAnswer = new JButton("Solve For Me");

    // Start Menu
    JFrame startMenu = new JFrame("Suduko"); // Start Menu
    JButton easyBtn = new JButton("Easy");
    JButton mediumBtn = new JButton("Medium");
    JButton hardBtn = new JButton("Hard");

    // Custom fonts
    Font pixelMplus;
    Font pixelMplusTitle;

    // Level Label
    JLabel lvlLabel;

    // Timer
    // Timer timer = new Timer();
    // TimerTask task;
    // int secondPassed = 0;
    // JLabel timerDisplay = new JLabel("00:00", SwingConstants.CENTER);

    // Constructor
    public SudokuMain() {
        // Load custom font
        try {
            URL url = getClass().getResource("PixelMplus10-Regular.ttf");
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File(url.getPath())).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File(url.getPath())));

            pixelMplusTitle = Font.createFont(Font.TRUETYPE_FONT, new File(url.getPath())).deriveFont(40f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File(url.getPath())));
        } catch (IOException | FontFormatException e) {

        }

        // Start Menu-----------------------------------
        startMenu.setLayout(new BorderLayout());
        // Title Panel
        JPanel title = new JPanel(new GridLayout(0, 1, 15, 5));
        JLabel label = new JLabel("SUDUKU", SwingConstants.CENTER);
        label.setFont(pixelMplusTitle);
        label.setBorder(new EmptyBorder(40, 0, 0, 0));
        title.add(label);

        // Level Option Panel
        easyBtn.setFont(pixelMplus);
        mediumBtn.setFont(pixelMplus);
        hardBtn.setFont(pixelMplus);
        JPanel center = new JPanel(new GridLayout(0, 1, 10, 10));
        center.add(easyBtn);
        center.add(mediumBtn);
        center.add(hardBtn);
        center.setBorder(new EmptyBorder(80, 80, 80, 80)); // top, left, btm, right

        startMenu.add(title, BorderLayout.NORTH);
        startMenu.add(center, BorderLayout.CENTER);
        startMenu.setSize(400, 400);
        startMenu.setVisible(true);

        // Buttons Listener
        AllButtonsListener listener = new AllButtonsListener();
        easyBtn.addActionListener(listener);
        mediumBtn.addActionListener(listener);
        hardBtn.addActionListener(listener);

        // Container Sudoku-----------------------------
        cp.setLayout(new BorderLayout());
        cp.add(board, BorderLayout.CENTER);

        JPanel flowPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(0, 1, 30, 10));
        gridPanel.setBorder(new EmptyBorder(5, 20, 0, 20));

        // Timer
        // task = new TimerTask() {
        // public void run() {
        // secondPassed++;
        // timerDisplay.setText(String.format("%02d:%02d", secondPassed / 60,
        // secondPassed % 60));
        // }
        // };

        TimeManagement.timerDisplay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));

        // Level Label
        lvlLabel = new JLabel("Test", SwingConstants.CENTER);
        lvlLabel.setFont(pixelMplus);

        // Add a button to the south to re-start the game
        btnNewGame.setFont(pixelMplus);
        btnNewGame.setPreferredSize(new Dimension(150, 30));
        btnShowAnswer.setFont(pixelMplus);
        btnShowAnswer.setPreferredSize(new Dimension(150, 30));

        // Restart Game
        btnNewGame.addActionListener(e -> {
            cp.dispose(); // Close the window
            startMenu.dispose();
            TimeManagement.resetTimer();
            SudokuMain sudoku = new SudokuMain();
        });

        // Instant Solve
        btnShowAnswer.addActionListener(e -> {
            if (!board.isSolved()) {
                board.solvePuzzle();
            } else {
                JOptionPane.showMessageDialog(null, "You have already solved");
            }
        });

        gridPanel.add(lvlLabel);
        gridPanel.add(TimeManagement.timerDisplay);
        gridPanel.add(btnNewGame);
        gridPanel.add(btnShowAnswer);

        flowPanel.add(gridPanel);
        cp.add(flowPanel, BorderLayout.EAST);

        pack(); // Pack the UI components, instead of setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
        setTitle("Sudoku");
        cp.setSize(1000, 800);
        cp.setVisible(false);
    }

//     public void playMusic(String path){
//         File audioFile = new File("file.wav");
//  URL musicUrl = getClass().getResource("/file.wav");
//          AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
//         AudioFormat format = audioStream.getFormat();
 
// DataLine.Info info = new DataLine.Info(Clip.class, format);
// Clip audioClip = (Clip) AudioSystem.getLine(info);
// audioClip.open(audioStream);
// audioClip.start();
//     }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // [TODO 1] Check Swing program template on how to run the constructor
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuMain sudokuMain = new SudokuMain();
            }
        });
    }

    private class AllButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String btnLabel = evt.getActionCommand();
            startMenu.setVisible(false);
            cp.setVisible(true);
            TimeManagement.startTimer(); // start timer

            // All the different method according to the level
            // chosen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Random random = new Random(); // initialize random
            // assign each difficulty level the number of shown cells
            // the harder, the lesser cell to show
            if (btnLabel.equals("Easy")) {
                board.init(random.nextInt(6) + 70);
                lvlLabel.setText("Difficulty: Easy");

            } else if (btnLabel.equals("Medium")) {
                board.init(random.nextInt(6) + 45);
                lvlLabel.setText("Difficulty: Medium");

            } else {
                board.init(random.nextInt(6) + 20);
                lvlLabel.setText("Difficulty: Hard");

            }
        }
    }

}
