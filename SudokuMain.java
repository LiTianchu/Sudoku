import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
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
    // JButton btnNewGame = new JButton("New Game");
    JButton btnNewGame;
    JFrame startMenu = new JFrame("Suduko"); // Start Menu
    JButton easyBtn = new JButton("Easy");
    JButton mediumBtn = new JButton("Medium");
    JButton hardBtn = new JButton("Hard");
    ImageIcon water = new ImageIcon("newGameBtn.png");
    Timer timer = new Timer();
    TimerTask task;
    int secondPassed = 0;
    JLabel timerDisplay = new JLabel("00:00", SwingConstants.CENTER);

    // Constructor
    public SudokuMain() {
        // Start Menu-----------------------------------
        startMenu.setLayout(new BorderLayout());
        // Title Panel
        JPanel title = new JPanel(new GridLayout(0, 1, 15, 5));
        JLabel label = new JLabel("SUDUKO", SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        label.setBorder(new EmptyBorder(40, 0, 0, 0));
        title.add(label);

        // Level Option Panel
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
        // btnNewGame.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        btnNewGame = new JButton(water);
        btnNewGame.setMargin(new Insets(0, 0, 0, 0)); // to remove the spacing between the image and button's borders
        btnNewGame.setBorder(null);
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
            if (btnLabel.equals("Easy")) {
                board.init();
            } else if (btnLabel.equals("Medium")) {
                board.init();
            } else {
                board.init();
            }
        }
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    };
}
