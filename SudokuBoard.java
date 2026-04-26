import java.util.*;
import java.io.*;

public class SudokuBoard {

   private char[][] board;
   
   public SudokuBoard(String filename) throws FileNotFoundException {
      board = new char[9][9];
      Scanner input = new Scanner(new File(filename));
      
      for(int r = 0; r < board.length; r++) {
         String line = input.nextLine();
         
         for(int c = 0; c < board[r].length; c++) {
            board[r][c] = line.charAt(c);
         }
      }
   
   }

   
   
   public boolean isValid() {
      return checkData() && checkRow() && checkCol() && miniSquare();
   }

   public boolean checkData() {
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[r].length; c++) {
            char value = board[r][c];

            if(!(value >= '1' && value <= '9') && value != '0') {
               return false;
            }
         }
      }
      return true;
   }

   public boolean validRows{
      for(int r = 0; r < board.length; r++) {
         Set<Character> seen = new HashSet<Character>();
         for(int c = 0; c < board[r].length; c++) {
            char value = board[r][c];

            if(value != '.') {
               if(seen.contains(value)){
                  return false;
               }
               seen.add(value);
            }
         }
      }
      return true;
   }

   public boolean validColumns {
      for(int c = 0; c < board[0].length; c++) {
         Set<Character> seen = new HashSet<Character>();
         for(int r = 0; r < board.length; r++) {
            char value = board[r][c];
            
         if(value != '.') {
            if(seen.contains(value)) {
            return false;
            }
         seen.add(value);
         }
         }   
      }
      return true;
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
      
      for(int r = 0; r < board.length; r++) {
         if(r % 3 == 0 && r != 0) {
            result += " ------.-------.------\n";
         }
         
         for(int c = 0; c < board[r].length; c++) {
            if(c % 3 == 0 && c != 0) {
               result += "| ";
            }
            result += board[r][c] + " ";
         
         }
         result += "\n";
      }
      return result;
   
   }

}
