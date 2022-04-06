import javax.swing.RowFilter;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.ArrayList;

public class Puzzle {

    // All variables have package access
    int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
    int[][][] availableNumEachGrid = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE][9];

    boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];

    // Constructor
    public Puzzle() {
        super(); // JPanel
    }

    public void newPuzzle(int numToGuess) {
        generateAvailableNums();

        // Hardcoded here for simplicity.
        // int[][] hardcodedNumbers = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
        // { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
        // { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
        // { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
        // { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
        // { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
        // { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
        // { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
        // { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };

        // for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
        // for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
        // numbers[row][col] = hardcodedNumbers[row][col];
        // }
        // }
        boolean successful = false;
        do {
            successful = generateNumbers();
            if (!successful) {
                numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
                generateAvailableNums();
            }
        } while (!successful);

        // Need to use numToGuess!
        // For testing, only 2 cells of "8" is NOT shown
        boolean[][] hardcodedIsShown = { { true, true, true, true, true, false, true, true, true },
                { true, true, true, true, true, true, true, true, false },
                { true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true },
                { true, true, true, true, true, true, true, true, true } };

        for (int row = 0; row < GameBoard.GRID_SIZE; ++row) {
            for (int col = 0; col < GameBoard.GRID_SIZE; ++col) {
                isShown[row][col] = hardcodedIsShown[row][col];
            }
        }
    }

    public boolean generateNumbers() {

        Random random = new Random();
        // int count = 0;
        for (int row = 0; row < numbers.length; row++) { // iterate through number grid
            for (int col = 0; col < numbers[row].length; col++) {
                int randomNum = 0;
                boolean isUnique = false;

                int availableCount = 0;

                ArrayList<Integer> availableNumberList = new ArrayList<Integer>(); // generate available number list

                for (int i = 0; i < availableNumEachGrid[row][col].length; i++) {
                    if (availableNumEachGrid[row][col][i] > 0 && availableNumEachGrid[row][col][i] < 10) { // check for
                                                                                                           // availability,
                                                                                                           // -1 for not
                                                                                                           // available
                        availableCount++;
                        availableNumberList.add(availableNumEachGrid[row][col][i]);
                    }

                }
                if (availableCount == 0) {
                    return false;
                }

                do {
                    // randomNum = random.nextInt(9) + 1;
                    randomNum = availableNumberList.get(random.nextInt(availableCount));// get a random element from the
                                                                                        // list
                    if (checkRow(row, randomNum) && checkColumn(col, randomNum)
                            && checkBlock(row - row % 3, col - col % 3, randomNum)) {
                        isUnique = true;
                    }
                } while (!isUnique);

                numbers[row][col] = randomNum;
            }
            // count++;
        }
        return true;

    }

    public boolean checkRow(int rowNum, int number) {
        boolean unique = true;
        for (int col = 0; col < GameBoard.GRID_SIZE; col++) {
            if (numbers[rowNum][col] == number) {
                unique = false;
            }
        }
        if (unique) { // remove the number from the availble list of that row
            for (int col = 0; col < GameBoard.GRID_SIZE; col++) {
                for (int num = 0; num < 9; num++) {
                    if (availableNumEachGrid[rowNum][col][num] == number) {
                        availableNumEachGrid[rowNum][col][num] = -1; // set to -1 for remove
                    }
                }
            }
        }
        return unique;
    }

    public boolean checkColumn(int colNum, int number) {
        boolean unique = true;
        for (int row = 0; row < GameBoard.GRID_SIZE; row++) {
            if (numbers[row][colNum] == number) {
                unique = false;
            }
        }
        if (unique) { // remove the number from the availble list of that column
            for (int row = 0; row < GameBoard.GRID_SIZE; row++) {
                for (int num = 0; num < 9; num++) {
                    if (availableNumEachGrid[row][colNum][num] == number) {
                        availableNumEachGrid[row][colNum][num] = -1; // set to -1 for remove
                    }
                }
            }
        }
        return unique;
    }

    public boolean checkBlock(int startingRow, int startingColumn, int number) {
        boolean unique = true;

        for (int row = startingRow; row < startingRow + 3; row++) {
            for (int col = startingColumn; col < startingColumn + 3; col++) {
                if (numbers[row][col] == number) {
                    unique = false;
                }
            }
        }

        if (unique) { // remove the number from the availble list of that block
            for (int row = startingRow; row < startingRow + 3; row++) {
                for (int col = startingColumn; col < startingColumn + 3; col++) {
                    for (int num = 0; num < 9; num++) {
                        if (availableNumEachGrid[row][col][num] == number) {
                            availableNumEachGrid[row][col][num] = -1; // set to -1 for remove
                        }
                    }
                }
            }

        }
        return unique;
    }

    public void generateAvailableNums() {
        for (int row = 0; row < GameBoard.GRID_SIZE; row++) {
            for (int col = 0; col < GameBoard.GRID_SIZE; col++) {
                for (int num = 0; num < 9; num++) {
                    availableNumEachGrid[row][col][num] = num + 1;
                }
            }
        }
    }
}
// (For advanced students) use singleton design pattern for this class

// private int[][] sudokuGrid;
// private int rowColNum;

// public Puzzle(int rowColNum) {
// sudokuGrid = new int[rowColNum][rowColNum];
// this.rowColNum = rowColNum;
// }
