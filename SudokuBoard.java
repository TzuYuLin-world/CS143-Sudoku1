import java.util.*;
import java.io.*;

public class SudokuBoard {

   private char[][] board;
   
   public SudokuBoard(String filename) throws FileNotFoundException {
      board = new char[9][9];
      Scanner input = new Scanner(new File(filename));
      
      for(int row = 0; row < 9; row++) {
         String line = input.nextLine();
         
         for(int col = 0; col < 9; col++) {
            board[row][col] = line.charAt(col);
         }
      }
   
   }
   
   public String toString() {
      String result = "";
      
      for(int row = 0; row < 9; row++) {
         if(row % 3 == 0 && row != 0) {
            result += " ------.-------.------\n";
         }
         
         for(int col = 0; col < 9; col++) {
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