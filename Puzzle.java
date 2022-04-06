import javax.swing.RowFilter;
import java.util.Random;

public class Puzzle {
   
        // All variables have package access
        int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
     
        // For testing, only 2 cells of "8" is NOT shown
        boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
     
        // Constructor
        public Puzzle() {
           super();  // JPanel
        }
     
        public void newPuzzle(int numToGuess) {
           // Hardcoded here for simplicity.
           int[][] hardcodedNumbers =
              {{5, 3, 4, 6, 7, 8, 9, 1, 2},
               {6, 7, 2, 1, 9, 5, 3, 4, 8},
               {1, 9, 8, 3, 4, 2, 5, 6, 7},
               {8, 5, 9, 7, 6, 1, 4, 2, 3},
               {4, 2, 6, 8, 5, 3, 7, 9, 1},
               {7, 1, 3, 9, 2, 4, 8, 5, 6},
               {9, 6, 1, 5, 3, 7, 2, 8, 4},
               {2, 8, 7, 4, 1, 9, 6, 3, 5},
               {3, 4, 5, 2, 8, 6, 1, 7, 9}};
     
           for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
              for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
                 numbers[row][col] = hardcodedNumbers[row][col];
              }
           }
     
           // Need to use numToGuess!
           // For testing, only 2 cells of "8" is NOT shown
           boolean[][] hardcodedIsShown =
              {{true, true, true, true, true, false, true, true, true},
               {true, true, true, true, true, true, true, true, false},
               {true, true, true, true, true, true, true, true, true},
               {true, true, true, true, true, true, true, true, true},
               {true, true, true, true, true, true, true, true, true},
               {true, true, true, true, true, true, true, true, true},
               {true, true, true, true, true, true, true, true, true},
               {true, true, true, true, true, true, true, true, true},
               {true, true, true, true, true, true, true, true, true}};
     
           for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
              for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
                 isShown[row][col] = hardcodedIsShown[row][col];
              }
           }
        }
     
        //(For advanced students) use singleton design pattern for this class
     }
    // private int[][] sudokuGrid;
    // private int rowColNum;

    // public Puzzle(int rowColNum) {
    //     sudokuGrid = new int[rowColNum][rowColNum];
    //     this.rowColNum = rowColNum;
    // }

    // public int[][] generate(){
    //     Random random = new Random();
    //   //  int count = 0;
    //     for(int row=0; row< sudokuGrid.length; row++) {
    //         for(int col=0; col< sudokuGrid[row].length; col++) {
    //          int randomNum = 0;
               
    //             boolean isUnique = false;
    //             do{
    //                 randomNum = random.nextInt(9) + 1;
    //             if(checkRow(row, randomNum) && checkColumn(sudokuGrid, col, randomNum) && checkBlock(sudokuGrid, row-row%3,col-col%3, randomNum)){
    //                 isUnique=true;
    //             }}while(!isUnique);

    //             sudokuGrid[row][col]=randomNum;
    //         }
    //     //    count++;
    //     }
    //     return sudokuGrid;
    // }

    // public boolean checkRow(int rowNum, int number) {
    //     boolean unique = true;
    //     for (int col = 0; col < rowColNum; col++) {
    //         if (sudokuGrid[rowNum][col] == number) {
    //             unique = false;
    //         }
    //     }
    //     if(unique){

    //     }
    //     return unique;
    // }

    // public boolean checkColumn(int[][] sudokuRowCol, int colNum,int number){
    //     boolean unique=true;
    //     for(int row=0; row<rowColNum;row++){
    //         if(sudokuRowCol[row][colNum]==number){
    //             unique=false;
    //         }
    //     }
    //     return unique;
    // }

    // public boolean checkBlock(int[][] sudokuRowCol, int startingRow, int startingColumn, int number){
    //     boolean unique=true;
        
    //     for(int row=startingRow; row<startingRow+3;row++){
    //         for(int col=startingColumn;col<startingColumn+3;col++){
    //            if( sudokuRowCol[row][col] == number){
    //             unique = false;
    //            }
    //         }
    //     }
    //     return unique;
    // }


