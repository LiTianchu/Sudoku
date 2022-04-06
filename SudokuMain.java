import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudokuMain extends JFrame {

   // private variables
   GameBoard board = new GameBoard();
   JButton btnNewGame = new JButton("New Game");

   // Constructor
   public SudokuMain() {
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(board, BorderLayout.CENTER);

      // Add a button to the south to re-start the game
      btnNewGame.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
      JPanel flowPanel = new JPanel(new FlowLayout());
      flowPanel.add(btnNewGame);
      cp.add(BorderLayout.EAST, flowPanel);

      // Restart Game
      btnNewGame.addActionListener(e -> {
         dispose(); // Close the window
         SudokuMain sudoku = new SudokuMain();
      });

      board.init();

      pack(); // Pack the UI components, instead of setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle window closing
      setTitle("Sudoku");
      setVisible(true);
   }

   /** The entry main() entry method */
   public static void main(String[] args) {
      // [TODO 1] Check Swing program template on how to run the constructor
      SudokuMain sudoku = new SudokuMain();
   }

}
