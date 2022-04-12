package sudoku;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JFrame {
    Container gameContainer = getContentPane(); 
    JButton btnNewGame = new JButton("New Game");
    JButton btnShowAnswer = new JButton("Solve For Me");
    JButton btnReset = new JButton("Reset");
    JButton btnHint = new JButton("Hint(3/3)");
    JLabel lvlLabel;

    public GamePanel(GameBoard board, String labelText) {
        gameContainer.setLayout(new BorderLayout());
        gameContainer.add(board, BorderLayout.CENTER);

        // Set attributes of the buttons
        btnNewGame.setFont(SudokuMain.pixelMplus);
        btnNewGame.setPreferredSize(new Dimension(150, 30));
        btnShowAnswer.setFont(SudokuMain.pixelMplus);
        btnShowAnswer.setPreferredSize(new Dimension(150, 30));
        btnReset.setFont(SudokuMain.pixelMplus);
        btnReset.setPreferredSize(new Dimension(150, 30));
        btnHint.setFont(SudokuMain.pixelMplus);
        btnHint.setPreferredSize(new Dimension(150, 30));

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
                JOptionPane.showMessageDialog(null, "You have already solved");
            }
        });

        btnReset.addActionListener(e -> board.initializeAllCells());

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
                JOptionPane.showMessageDialog(null, "You have already solved");
            } else {
                JOptionPane.showMessageDialog(null, "You have used up all your hints");
            }
        });
        JPanel flowPanel = new JPanel(new FlowLayout());
        JPanel gridPanel = new JPanel(new GridLayout(0, 1, 30, 10));
        gridPanel.setBorder(new EmptyBorder(5, 20, 0, 20));
        TimeManagement.timerDisplay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));

        // Level Label
        lvlLabel = new JLabel(labelText, SwingConstants.CENTER);
        lvlLabel.setFont(SudokuMain.pixelMplus);
        gridPanel.add(lvlLabel);
        gridPanel.add(TimeManagement.timerDisplay);
        gridPanel.add(btnNewGame);
        gridPanel.add(btnShowAnswer);
        gridPanel.add(btnReset);
        gridPanel.add(btnHint);

        flowPanel.add(gridPanel);
        gameContainer.add(flowPanel, BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Handle window closing
        setTitle("Sudoku");
        setSize(1000, 800);
        setVisible(true);
    }
}
