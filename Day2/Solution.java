import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int[] revisedRussianRoulette(int[] doors) {
        int[] ans = new int[2];
        
        ans[1] = maxR(doors);
        ans[0] = minR(doors);
        
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] doors = new int[n];
        for(int doors_i = 0; doors_i < n; doors_i++){
            doors[doors_i] = in.nextInt();
        }
        int[] result = revisedRussianRoulette(doors);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");


        in.close();
    }
    
    public static int maxR(int[] doors){
        int count = 0;
        for (int i = 0; i < doors.length; i++){
            if (doors[i] == 1){
                count++;
            }
        }
        return count;
    }
    
    public static int minR(int[] doors){
        int count = 0;
        for (int i = 0; i < doors.length - 1; i++){
            if (doors[i] == 1){
                doors[i] = 0;
                if (doors[i+1] == 1){
                    doors[i+1] = 0;
                    i++;
                }
                count++;
            }
        }
        count = (doors[doors.length - 1] == 1)?(count + 1):count;
        return count;
    }
}