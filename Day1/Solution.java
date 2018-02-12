import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String acidNaming(String acid_name) {
        if (icCheck(acid_name)){
            if (hydroCheck(acid_name)){
                return "non-metal acid";
            } else {
                return "polyatomic acid";
            }
        } else {
            return "not an acid";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            String acid_name = in.next();
            String result = acidNaming(acid_name);
            System.out.println(result);
        }
        in.close();
    }
    
    static Boolean icCheck(String word){
        String x = substringEnd(word);
        return (x.equals("ic"))?true:false;
    }
    
    static Boolean hydroCheck(String word){
        String x = substringBegin(word);
        return (x.equals("hydro"))?true:false;
    }
    
    static String substringBegin(String word){
        return word.substring(0, Math.min(word.length(), 5));
    }
    
    static String substringEnd(String word){
        return (word.length() > 2)?word.substring(word.length() - 2):word;
    }
}
