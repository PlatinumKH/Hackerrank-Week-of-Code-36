import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long raceAgainstTime(int n, int mason_height, int[] heights, int[] prices) {
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int mason_height = in.nextInt();
        int[] heights = new int[n-1];
        for(int heights_i = 0; heights_i < n-1; heights_i++){
            heights[heights_i] = in.nextInt();
        }
        int[] prices = new int[n-1];
        for(int prices_i = 0; prices_i < n-1; prices_i++){
            prices[prices_i] = in.nextInt();
        }
        long result = raceAgainstTime(n, mason_height, heights, prices);
        System.out.println(result);
        in.close();
    }
}