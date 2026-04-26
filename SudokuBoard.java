import java.util.*;
import java.io.*;

public class SudokuBoard {

   private char[][] board;
   
   public SudokuBoard(String filename) throws FileNotFoundException {
      board = new char[9][9];
      Scanner input = new Scanner(new File(filename));
      
      for(int row = 0; row < board.length; row++) {
         String line = input.nextLine();
         
         for(int col = 0; col < board[row].length; col++) {
            board[row][col] = line.charAt(col);
         }
      }
   
   }

   public boolean isValid() {
      return checkData() && checkRow() && checkCol() && miniSquare();
   }
   
   private int[][] miniSquare(int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   
   public boolean isSolved() {}
   
   public String toString() {
      String result = "";
      
      for(int row = 0; row < board.length; row++) {
         if(row % 3 == 0 && row != 0) {
            result += " ------.-------.------\n";
         }
         
         for(int col = 0; col < board[row].length; col++) {
            if(col % 3 == 0 && col != 0) {
               result += "| ";
            }
            result += board[row][col] + " ";
         
         }
         result += "\n";
      }
      return result;
   
   }

}
