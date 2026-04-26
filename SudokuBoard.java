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
      return checkData() && checkRow() && checkCol() && checkMiniSquare();
   }

   private boolean checkData() {
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[r].length; c++) {
            char value = board[r][c];

            if(!(value >= '1' && value <= '9') && value != '.') {
               return false;
            }
         }
      }
      return true;
   }

   private boolean checkRow() {
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

   private boolean checkCol() {
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

   
   private boolean checkMiniSquare() {
      for(int spot = 1; spot <= 9; spot++) {
         char[][] mini = miniSquare(spot);
         for(int r = 0; r < 3; r++) {
            
            for(int c = 0; c < 3; c++) {
               char value = mini[r][c];
               if(value != '0') {
                  for(int r2 = 0; r2 < 3; r2++) {
                     for(int c2 = 0; c2 < 3; c2++) {
                        if(!(r == r2 && c == c2) && value == mini[r2][c2]) {
                           return false;
                        }
                     }
                  }
               }

            }
         }
      }
   }
   
   
   private char[][] miniSquare(int spot) {
      char[][] mini = new char[3][3];
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
   
   public boolean isSolved() {
      Map<Character, Integer> counts = new HashMap<Character, Integer>();
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[r].length; c++) {
            char value = board[r][c];

            if(value >= '1' && value <= '9') {
               if(!counts.containsKey(value)) {
                  counts.put(value, 1);
               } else {
                  counts.put(value, counts.get(value) + 1);
               }
            }
         }
      }
      for(char num = '1'; num <= '9'; num++) {
         if(!counts.containsKey(num) || counts.get(num) != 9) {
            return false;
         }
      }
      return isValid();
   }
   
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
