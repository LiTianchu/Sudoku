import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class SudokuMain extends JFrame {

    // private variables
    GameBoard board = new GameBoard();
    // Container cp = getContentPane(); // Container Sudoku
    JFrame cp = new JFrame("Suduko"); // Sudoku Menu
    JButton btnNewGame = new JButton("New Game");

    // Start Menu
    JFrame startMenu = new JFrame("Suduko"); // Start Menu
    JButton easyBtn = new JButton("Easy");
    JButton mediumBtn = new JButton("Medium");
    JButton hardBtn = new JButton("Hard");

    // Custom fonts
    Font pixelMplus;
    Font pixelMplusTitle;

    // Timer
    Timer timer = new Timer();
    TimerTask task;
    int secondPassed = 0;
    JLabel timerDisplay = new JLabel("00:00", SwingConstants.CENTER);

    // Constructor
    public SudokuMain() {
        // Load custom font
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")));

            pixelMplusTitle = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")).deriveFont(40f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        // Start Menu-----------------------------------
        startMenu.setLayout(new BorderLayout());
        // Title Panel
        JPanel title = new JPanel(new GridLayout(0, 1, 15, 5));
        JLabel label = new JLabel("SUDUKO", SwingConstants.CENTER);
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

        // Add a button to the south to re-start the game
        btnNewGame.setFont(pixelMplus);
        btnNewGame.setPreferredSize(new Dimension(150, 40));

        JPanel flowPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        gridPanel.add(btnNewGame);

        // Restart Game
        btnNewGame.addActionListener(e -> {
            cp.dispose(); // Close the window
            startMenu.dispose();
            SudokuMain sudoku = new SudokuMain();
        });

        // Timer
        task = new TimerTask() {
            public void run() {
                secondPassed++;
                timerDisplay.setText(String.format("%02d:%02d", secondPassed / 60, secondPassed % 60));
            }
        };

        timerDisplay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        gridPanel.add(timerDisplay);
        flowPanel.add(gridPanel);
        cp.add(flowPanel, BorderLayout.EAST);

        // board.init();

        pack(); // Pack the UI components, instead of setSize()
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
        setTitle("Sudoku");
        cp.setSize(1000, 800);
        cp.setVisible(false);
    }

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
            startTimer(); // start timer

            // All the different method according to the level
            // chosen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Random random = new Random(); // initialize random
            // assign each difficulty level the number of shown cells
            // the harder, the lesser cell to show
            if (btnLabel.equals("Easy")) {
                board.init(random.nextInt(6) + 70);
            } else if (btnLabel.equals("Medium")) {
                board.init(random.nextInt(6) + 45);
            } else {
                board.init(random.nextInt(6) + 20);
            }
        }
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    };
}
