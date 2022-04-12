package sudoku;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JFrame {
    Container gameContainer = getContentPane();
    JButton btnNewGame = new JButton("New Game");
    JButton btnShowAnswer = new JButton("Solve For Me");
    JButton btnReset = new JButton("Reset Cells");
    JButton btnHint = new JButton("Hint(3/3)");
    JButton btnRestart = new JButton("Restart");
    JLabel lvlLabel;

    public GamePanel(GameBoard board, String labelText) {
        gameContainer.setLayout(new BorderLayout());
        gameContainer.add(board, BorderLayout.CENTER);

        // Set attributes of the buttons
        btnNewGame.setFont(SudokuMain.pixelMplus);
        btnNewGame.setPreferredSize(new Dimension(150, 20));
        btnNewGame.setBackground(SudokuMain.darkblue);
        btnNewGame.setForeground(Color.white);

        btnShowAnswer.setFont(SudokuMain.pixelMplus);
        btnShowAnswer.setPreferredSize(new Dimension(150, 20));
        btnShowAnswer.setBackground(SudokuMain.darkblue);
        btnShowAnswer.setForeground(Color.white);

        btnReset.setFont(SudokuMain.pixelMplus);
        btnReset.setPreferredSize(new Dimension(150, 20));
        btnReset.setBackground(SudokuMain.darkblue);
        btnReset.setForeground(Color.white);

        btnHint.setFont(SudokuMain.pixelMplus);
        btnHint.setPreferredSize(new Dimension(150, 20));
        btnHint.setBackground(SudokuMain.darkblue);
        btnHint.setForeground(Color.white);

        btnRestart.setFont(SudokuMain.pixelMplus);
        btnRestart.setPreferredSize(new Dimension(150, 20));
        btnRestart.setBackground(SudokuMain.darkblue);
        btnRestart.setForeground(Color.white);

        JPanel flowPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(0, 1, 50, 30));
        setIconImage(SudokuMain.sudokuIcon.getImage());
        // Restart Game
        btnNewGame.addActionListener(e -> {
            this.dispose(); // Close the window

            TimeManagement.resetTimer();
            SudokuMain sudoku = new SudokuMain();
        });

        // Instant Solve
        btnShowAnswer.addActionListener(e -> {
            if (!board.isSolved()) {
                board.solvePuzzle();
            } else {
                displaySolvedMsg();
            }
        });

        btnReset.addActionListener(e -> {
            if (!board.isSolved()) {
                board.initializeAllCells();
            } else {
                displaySolvedMsg();
            }
        });

        btnHint.addActionListener(e -> {
            int numOfHintsLeft = Integer.parseInt(btnHint.getText().substring(5, 6));
            if (!board.isSolved() && numOfHintsLeft != 0) {
                numOfHintsLeft--;
                btnHint.setText("Hint(" + numOfHintsLeft + "/3)");
                board.Hint();
                if (board.isSolved()) {
                    board.showCongrats();
                }
            } else if (board.isSolved()) {
                displaySolvedMsg();
            } else {
                JOptionPane.showMessageDialog(null, "You have used up all your hints");
            }

            if (numOfHintsLeft == 0) {
                btnHint.setBackground(SudokuMain.myRed);
                btnHint.setForeground(Color.white);
            }
        });

        btnRestart.addActionListener(e -> {

            int input = JOptionPane.showConfirmDialog(null, "Do you want to restart?");

            if(input==0){ //0 for yes

                new GamePanel(board, labelText);
                board.initializeAllCells();
                TimeManagement.resetTimer();
                TimeManagement.startTimer();
                this.dispose();
            }

        });

        gridPanel.setBorder(new EmptyBorder(5, 20, 0, 20));
        TimeManagement.timerDisplay.setFont(new Font("Monospaced", Font.BOLD, 40));

        // Level Label
        lvlLabel = new JLabel("Difficulty: " + labelText, SwingConstants.CENTER);
        lvlLabel.setFont(SudokuMain.pixelMplus);
        lvlLabel.setForeground(Color.BLUE);
        
        gridPanel.add(lvlLabel);
        gridPanel.add(TimeManagement.timerDisplay);
        gridPanel.add(btnNewGame);
        gridPanel.add(btnShowAnswer);
        gridPanel.add(btnReset);
        gridPanel.add(btnHint);
        gridPanel.add(btnRestart);

        flowPanel.add(gridPanel);
        gameContainer.add(flowPanel, BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Handle window closing
        setTitle("Sudoku");
        setSize(1000, 800);
        setVisible(true);
    }

    public void displaySolvedMsg() {
        JOptionPane.showMessageDialog(null, "You have already solved the puzzle");
    }
}
