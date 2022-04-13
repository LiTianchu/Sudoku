package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GameBoard extends JPanel {
   public static final int GRID_SIZE = 9; // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

   // Name-constants for UI sizes
   public static final int CELL_SIZE = 60; // Cell width/height in pixels
   public static final int BOARD_WIDTH = CELL_SIZE * GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;
   // Board width/height in pixels

   // The game board composes of 9x9 "Customized" JTextFields,
   private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];
   // It also contains a Puzzle
   private Puzzle puzzle = new Puzzle();

   private int rowCount = 0;
   private int colCount = 0;

   private Music music = new Music();

   // Constructor
   public GameBoard() {
      super.setLayout(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE)); // JPanel

      for (int index = 0; index < 9; index++) { // Big Box has 9 mini boxes
         SubBoard board = new SubBoard(rowCount, colCount, index);

         if (index == 2 || index == 5) {
            colCount = 0;
            rowCount = rowCount + 3;
         } else {
            colCount = colCount + 3;
         }
         super.add(board);
      }

      // [TODO 3] Allocate a common listener as the ActionEvent listener for all the
      // Cells (JTextFields)
      // CellInputListener listener = new CellInputListener();
      AddKeyListener validateListener = new AddKeyListener();

      // [TODO 4] Every editable cell adds this common listener
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].isEditable()) {
               cells[row][col].addKeyListener(validateListener); // Validate input
            }
         }
      }

      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }

   public class SubBoard extends JPanel {
      public SubBoard(int startRow, int startCol, int index) {
         setLayout(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
         setBorder(new LineBorder(Color.black, 1));

         if (index % 2 == 1) {
            setBackground(SudokuMain.skyblue);
         } else {
            setBackground(Color.white);
         }

         for (int row = startRow; row < startRow + 3; row++) {
            for (int col = startCol; col < startCol + 3; col++) {
               cells[row][col] = new Cell(row, col);
               add(cells[row][col]); // JPanel
            }
         }
      }
   }

   /**
    * Initialize the puzzle number, status, background/foreground color,
    * of all the cells from puzzle[][] and isRevealed[][].
    * Call to start a new game.
    */
   public void init(int numToShow) {

      // Get a new puzzle
      puzzle.newPuzzle(numToShow);

      // Based on the puzzle, initialize all the cells.
      initializeAllCells(true);
   }

   public void solvePuzzle() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].isEditable()) {
               cells[row][col].status = CellStatus.REVEALED;
               cells[row][col].paint();

            }
         }
      }

      showCongrats();
   }

   // method to initialize the cells
   public void initializeAllCells(boolean resetAll) {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].isEditable() || resetAll) {
               cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
            }
         }
      }
   }

   // method to reveal on cell when hint is pressed
   public void hint() {
      boolean hinted = false;
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].isEditable() && cells[row][col].status != CellStatus.CORRECT_GUESS
                  && cells[row][col].status != CellStatus.REVEALED && !hinted) {
               cells[row][col].status = CellStatus.REVEALED;
               cells[row][col].paint();
               hinted = true;
            }
         }
      }
   }

   /*
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of NO_GUESS or WRONG_GUESS
    */
   public boolean isSolved() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.NO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }

   public void showCongrats() {

      music.stopMusic();
      music.playCongratMusic();

      new java.util.Timer().schedule(
            new java.util.TimerTask() {
               @Override
               public void run() {
                  music.playMusic();
               }
            },
            8000);
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col].status = CellStatus.SHOWN;
            cells[row][col].paint();
         }
      }
      int timeSpent = TimeManager.getTime();
      TimeManager.stopTimer();

      JOptionPane.showMessageDialog(null,
            String.format("Congratulations! Time Spend: %dhours %dminutes and %dseconds", timeSpent / 60 / 60,
                  timeSpent / 60 % 60,
                  timeSpent % 60));
   }

   // [TODO 2] Define a Listener Inner Class
   private class AddKeyListener implements KeyListener {
      @Override
      public void keyPressed(KeyEvent ke) {
         Cell sourceCell = (Cell) ke.getSource();
         int numberIn = -1;

         if (ke.getKeyChar() >= '1' && ke.getKeyChar() <= '9' && sourceCell.status != CellStatus.REVEALED
               && sourceCell.status != CellStatus.SHOWN) {
            sourceCell.setEditable(true);
            sourceCell.setText("");
            numberIn = ke.getKeyChar() - 48;

            // * [TODO 5]
            // * Check the numberIn against sourceCell.number.
            // * Update the cell status sourceCell.status,
            // * and re-paint the cell via sourceCell.paint().
            // */
            if (numberIn == sourceCell.number) {
               sourceCell.status = CellStatus.CORRECT_GUESS;
            } else if (numberIn == 0) {
               sourceCell.status = CellStatus.NO_GUESS;
            } else {
               sourceCell.status = CellStatus.WRONG_GUESS;
            }
            sourceCell.paint();

            // * [TODO 6][Later] Check if the player has solved the puzzle after this move,
            // * by call isSolved(). Put up a congratulation JOptionPane, if so.
            // */
            if (isSolved()) {

               showCongrats();
            }

         } else {
            sourceCell.setEditable(false);
         }

      }

      @Override
      public void keyReleased(KeyEvent ke) {
      }

      @Override
      public void keyTyped(KeyEvent ke) {
      }

   }

}
