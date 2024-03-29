package sudoku;

public enum CellStatus {
   SHOWN, // number shown, no need to guess
   NO_GUESS, // need to guess - not attempted yet
   CORRECT_GUESS, // need to guess - correct guess
   WRONG_GUESS, // need to guess - wrong guess
   REVEALED // revealed number
   // The puzzle is solved if none of the cells have
   // status of NO_GUESS or WRONG_GUESS
}