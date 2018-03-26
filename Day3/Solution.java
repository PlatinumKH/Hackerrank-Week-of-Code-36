import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int waysToGiveACheck(char[][] board) {
        int pos = -1;
        for (int i = 0; ((i < 8) && (pos == -1)); i++){
            if (validMove(board, i)){
                pos = i;
            }
        }

        if (allyPiecesCap(board, pos)){
            return 4;
        } else {
            int count = 0;

            if (queenCap(board, pos))count++;
            if (rookCap(board, pos))count++;
            if (bishopCap(board, pos))count++;
            if (knightCap(board, pos))count++;

            return count;
        }
    }

    static boolean validMove(char[][] board, int i){

        // If the space in front of the pawn isn't empty, the
        // pawn can't move there
        if (!((board[1][i] == 'P') && (board[0][i] == '#'))){
            return false;
        }

        boolean cap = false;

        board[0][i] = 'P';
        board[1][i] = '#';

        for (int x = 0; ((x < 8) && (!cap)); x++){
            for (int y = 0; ((y < 8) && (!cap)); y++){
                switch(board[x][y]){
                    case 'q': cap = straightCap(board, x, y, 'K') || diagCap(board, x, y, 'K'); break;
                    case 'n': cap = knightCap(board, x, y, 'K'); break;
                    case 'b': cap = diagCap(board, x, y, 'K'); break;
                    case 'r': cap = straightCap(board, x, y, 'K'); break;
                }
            }
        }

        if (cap){
            board[0][i] = '#';
            board[1][i] = 'P';
        }

        // If our king can be captured, we want to return a false value to
        // indicate this is not a valid move
        return !cap;


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

    static boolean diagCap(char[][] board, int x, int y, char king){
        
        //Down-Right
        for (int offset = 1; (((x + offset) < 8) && ((y + offset) < 8) ); offset++){
            if (board[x + offset][y + offset] == king) return true;
            if (board[x + offset][y + offset] != '#') break;
        }

        //Down-Left

        for (int offset = 1; (((x + offset) < 8) && ((y - offset) >= 0) ); offset++){
            if (board[x + offset][y - offset] == king) return true;
            if (board[x + offset][y - offset] != '#') break;
        }

        //Up-Right

        for (int offset = 1; (((x - offset) >= 0) && ((y + offset) < 8) ); offset++){
            if (board[x - offset][y + offset] == king) return true;
            if (board[x - offset][y + offset] != '#') break;
        }

        //Up-Left

        for (int offset = 1; (((x - offset) >= 0) && ((y - offset) >= 0) ); offset++){
            if (board[x - offset][y - offset] == king) return true;
            if (board[x - offset][y - offset] != '#') break;
        }

        return false;

    }

    static boolean straightCap(char[][] board, int x, int y, char king){

        //Down
        for (int i = 1; (x + i) < 8; i++){
            if (board[x + i][y] == king) return true;
            if (board[x + i][y] != '#') break;
        }

        //Right
        for (int i = 1; (y + i) < 8; i++){
            if (board[x][y + i] == king) return true;
            if (board[x][y + i] != '#') break;
        }

        //Left
        for (int i = 1; (y - i) >= 0; i++){
            if (board[x][y - i] == king) return true;
            if (board[x][y - i] != '#') break;
        }

        //Up
        for (int i = 1; (x - i) >= 0; i++){
            if (board[x - i][y] == king) return true;
            if (board[x - i][y] != '#') break;
        }

        return false;
    }

    static boolean knightCap(char[][] board, int x, int y, char king){

        if ((y - 2) >= 0){
            if ((x != 7) && (board[x + 1][y - 2] == king)) return true;

            if ((x != 0) && (board[x - 1][y - 2] == king)) return true;
        }

        if ((y - 1) >= 0){
            if ((x < 6) && (board[x + 2][y - 1] == king)) return true;

            if ((x > 1) && (board[x - 2][y - 1] == king)) return true;
        }

        if ((y + 1) < 8){
            if ((x < 6) && (board[x + 2][y + 1] == king)) return true;

            if ((x > 1) && (board[x - 2][y + 1] == king)) return true;
        }

        if ((y + 2) < 8){
            if ((x != 7) && (board[x + 1][y + 2] == king)) return true;

            if ((x != 0) && (board[x - 1][y + 2] == king)) return true;
        }

        return false;
    }

    static boolean allyPiecesCap(char[][] board, int pos){

        boolean cap = false;

        board[0][pos] = 'P';
        board[1][pos] = '#';

        for (int x = 0; ((x < 8) && (!(cap))); x++){
            for (int y = 0; ((y < 8) && (!(cap))); y++){
                switch(board[x][y]){
                    case 'Q': cap = (straightCap(board, x, y, 'k') || diagCap(board, x, y, 'k')); break;
                    case 'N': cap = knightCap(board, x, y, 'k'); break;
                    case 'B': cap = diagCap(board, x, y, 'k'); break;
                    case 'R': cap = straightCap(board, x, y, 'k'); break;
                }
            }
        }

        board[0][pos] = '#';
        board[1][pos] = 'P';

        

        return cap;

    }


}