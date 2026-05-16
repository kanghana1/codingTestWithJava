import java.util.*;

class Solution {
    static boolean[] spoiler;
    static Map<String, Integer> map = new HashMap<>();
    
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        spoiler = new boolean[message.length()];
        for (int i = 0 ; i < spoiler_ranges.length ; i++) {
            for (int j = spoiler_ranges[i][0] ; j <= spoiler_ranges[i][1] ; j++) {
                spoiler[j] = true;
            }
        }
        
        String[] str = message.split(" ");
        int sIdx = 0;
        for (int i = 0 ; i < str.length ; i++) {
            int eIdx = sIdx + str[i].length() - 1;
            if (!spoilerString(sIdx, eIdx)) map.put(str[i], 1);
            sIdx = eIdx + 2;
        }
        
        for (int i = 0 ; i < spoiler_ranges.length ; i++) {
            int s = spoiler_ranges[i][0];
            int e = spoiler_ranges[i][1];
            
            if (s == e && message.charAt(s) == ' ') continue;
            
            while (s >= 0 && message.charAt(s) != ' ') s--;
            while (e < message.length() && message.charAt(e) != ' ') e++;
            
            // System.out.println(message.substring(s + 1, e));
                
            for (String ans : message.substring(s + 1, e).split(" ")) {
                if (map.get(ans) == null) {
                    answer++;
                }
                map.put(ans, 1);
            }
        }
        
        return answer;
    }
    
    private static boolean spoilerString(int s, int e) {
        for (int i = s ; i <= e ; i++) {
            if (spoiler[i]) return true;
        }
        return false;
    }
}