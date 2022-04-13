package sudoku;

import java.util.Random;
import java.util.ArrayList;

public class Puzzle {

    // All variables have package access
    int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
    int[][][] availableNumEachGrid = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE][9];

    int[][] shownNums = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
    boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];

    //random number generator
    Random random = new Random();

    // Constructor
    public Puzzle() {
        super(); // JPanel
    }

    public void newPuzzle(int numToShow) {

        //generate an list of available numbers for each cell
        generateAvailableNums();
        //generate a list of true/false for show/hide the numbers
        generateShownNums(numToShow);
      
        boolean generationSuccessful = false;
        //generate random puzzle
        do {
            generationSuccessful = generateNumbers();
            if (!generationSuccessful) { // reset to initial states
                numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];
                generateAvailableNums();
            }
        } while (!generationSuccessful);

    }

    public boolean generateNumbers() {

        random = new Random();
        ArrayList<Integer> availableNumberList;
        int randomNum;
        boolean isUnique;
        int availableCount;

        for (int row = 0; row < numbers.length; row++) { // iterate through number grid
            for (int col = 0; col < numbers[row].length; col++) {
                randomNum = 0;
                isUnique = false;
                availableCount = 0;

                availableNumberList = new ArrayList<Integer>(); // generate available number list

                // check for availability
                for (int i = 0; i < availableNumEachGrid[row][col].length; i++) {
                    if (availableNumEachGrid[row][col][i] > 0 && availableNumEachGrid[row][col][i] < 10) {
                        availableCount++;
                        availableNumberList.add(availableNumEachGrid[row][col][i]);
                    }

                }

                if (availableCount == 0) {
                    // if no available number, means conflict is detected
                    // return false to retry generating
                    return false;
                }

                do {
                    // get a random element from the list of available numbers
                    randomNum = availableNumberList.get(random.nextInt(availableCount));
                    if (checkRow(row, randomNum) && checkColumn(col, randomNum)
                            && checkBlock(row - row % 3, col - col % 3, randomNum)) {
                        isUnique = true;
                    }
                } while (!isUnique);

                numbers[row][col] = randomNum;
            }
            
        }
        return true;

    }

    public void generateShownNums(int numToShow) {
        random = new Random();
        for (int num = numToShow; num > 0; num--) {

            boolean isChanged = false;
            do {
                // change a random cell to true
                int randomInt1 = random.nextInt(GameBoard.GRID_SIZE);
                int randomInt2 = random.nextInt(GameBoard.GRID_SIZE);
                // do not change if the cell is already true
                if (!isShown[randomInt1][randomInt2]) {
                    isShown[randomInt1][randomInt2] = true;
                    isChanged = true;
                }

            } while (!isChanged);// repeat until it is filled

        }

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
                for (int num = 0; num < GameBoard.GRID_SIZE; num++) {
                    availableNumEachGrid[row][col][num] = num + 1;
                }
            }
        }
    }
}

