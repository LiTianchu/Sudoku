import javax.swing.RowFilter;
import java.util.Random;

public class SdkNumGenerator {

    private int[][] sudokuGrid;
    private int rowColNum;

    public SdkNumGenerator(int rowColNum) {
        sudokuGrid = new int[rowColNum][rowColNum];
        this.rowColNum = rowColNum;
    }

    public int[][] generate(){
        Random random = new Random();
      //  int count = 0;
        for(int row=0; row< sudokuGrid.length; row++) {
            for(int col=0; col< sudokuGrid[row].length; col++) {
             int randomNum = 0;
               
                boolean isUnique = false;
                do{
                    randomNum = random.nextInt(9) + 1;
                if(checkRow(row, randomNum) && checkColumn(sudokuGrid, col, randomNum) && checkBlock(sudokuGrid, row-row%3,col-col%3, randomNum)){
                    isUnique=true;
                }}while(!isUnique);

                sudokuGrid[row][col]=randomNum;
            }
        //    count++;
        }
        return sudokuGrid;
    }

    public boolean checkRow(int rowNum, int number) {
        boolean unique = true;
        for (int col = 0; col < rowColNum; col++) {
            if (sudokuGrid[rowNum][col] == number) {
                unique = false;
            }
        }
        if(unique){

        }
        return unique;
    }

    public boolean checkColumn(int[][] sudokuRowCol, int colNum,int number){
        boolean unique=true;
        for(int row=0; row<rowColNum;row++){
            if(sudokuRowCol[row][colNum]==number){
                unique=false;
            }
        }
        return unique;
    }

    public boolean checkBlock(int[][] sudokuRowCol, int startingRow, int startingColumn, int number){
        boolean unique=true;
        
        for(int row=startingRow; row<startingRow+3;row++){
            for(int col=startingColumn;col<startingColumn+3;col++){
               if( sudokuRowCol[row][col] == number){
                unique = false;
               }
            }
        }
        return unique;
    }


}