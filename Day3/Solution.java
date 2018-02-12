import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int waysToGiveACheck(char[][] board) {
        int count = 0;
        int pos = -1;
        for (int i = 0; ((i < 8) && (pos == -1)); i++){
            if ((board[1][i] == 'P') && (board[0][i] == '#')){
                pos = i;
            }
        }

            board[1][pos] = '#';
            board[0][pos] = 'P';

            if (queenCap(board, pos))count++;
            if (rookCap(board, pos))count++;
            if (bishopCap(board, pos))count++;
            if (knightCap(board, pos))count++;

            return count;
        }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            char[][] board = new char[8][8];
            for(int board_i = 0; board_i < 8; board_i++){
                String line = in.next();
                for(int board_j = 0; board_j < 8; board_j++){
                    board[board_i][board_j] = line.charAt(board_j);
                }
            }
            int result = waysToGiveACheck(board);
            System.out.println(result);
        }
        in.close();
    }

    static boolean queenCap(char[][] board, int pos){
    	return (straightCap(board, pos) || diagCap(board,pos));
    }

    static boolean rookCap(char[][] board, int pos){
    	return straightCap(board, pos);
    }

    static boolean bishopCap(char[][] board, int pos){
    	return diagCap(board, pos);
    }

    static boolean knightCap(char[][] board, int pos){
    	// Since the knight will be on the top row (after promotion)
    	// to consider 4 spaces below the knight

    	//Bottom Left
    	if ((pos - 2 >= 0) && board[1][pos - 2] == 'k') return true;
    	if ((pos - 1 >= 0) && board[2][pos - 1] == 'k') return true;

    	//Bottom Right
    	if ((pos + 2 < 8) && board[1][pos + 2] == 'k') return true;
    	if ((pos + 1 < 8) && board[2][pos + 1] == 'k') return true;

    	return false;
    }

    static boolean diagCap(char[][] board, int pos){
    	//Down-Right

    	for (int i = 1; (pos + i) < 8; i++ ){
    		if (board[i][pos + i] == 'k') return true;
    		if (board[i][pos + i] != '#') break;
    	}

    	//Down-Left
    	for (int i = 1; (pos - i) >= 0; i++ ){
    		if (board[i][pos - i] == 'k') return true;
    		if (board[i][pos - i] != '#') break;
    	}

    	return false;

    }

    static boolean straightCap(char[][] board, int pos){

    	//Down
    	for (int i = 1; i < 8; i++){
    		if (board[i][pos] == 'k') return true;
    		if (board[i][pos] != '#') break;
    	}

    	//Right
    	for (int i = pos + 1; i < 8; i++){
    		if (board[0][i] == 'k') return true;
    		if (board[0][i] != '#') break;
    	}

    	//Left
    	for (int i = pos - 1; i >= 0; i--){
    		if (board[0][i] == 'k') return true;
    		if (board[0][i] != '#') break;
    	}

    	return false;
    }
}