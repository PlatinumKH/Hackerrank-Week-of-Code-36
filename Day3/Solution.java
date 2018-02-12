import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int waysToGiveACheck(char[][] board) {
        int count = 0;
        for (int i = 0; i < 8; i++){
            if ((board[1][i] == 'P') && (board[0][i] == '#')){
                int [][] hold = new int[board.length][];
                for(int i = 0; i < board.length; i++) hold[i] = board[i].clone();
                
                hold[1][i] = '#';
                hold[0][i] = 'P';
                
                if (queenCap(board))count++;
                if (rookCap(board))count++;
                if (bishopCap(board))count++;
                if (knightCap(board))count++;
                
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            char[][] board = new char[8][8];
            for(int board_i = 0; board_i < 8; board_i++){
                for(int board_j = 0; board_j < 8; board_j++){
                    board[board_i][board_j] = in.next().charAt(0);
                }
            }
            int result = waysToGiveACheck(board);
            System.out.println(result);
        }
        in.close();
    }
}