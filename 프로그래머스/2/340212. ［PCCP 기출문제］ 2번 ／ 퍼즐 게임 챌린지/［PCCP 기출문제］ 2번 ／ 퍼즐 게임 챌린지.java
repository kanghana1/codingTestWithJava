import java.util.*;
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 0;
        for (int d : diffs) right = Math.max(right, d); 
        int answer = right;
        
        
        while (left < right) {
            int level = (left + right) / 2;
            long time = 0;
            for (int i = 0 ; i < diffs.length ; i++) {
                if (diffs[i] > level) {
                    time += ((diffs[i] - level) * (times[i] + times[i - 1]) + times[i]);
                } else {
                    time += times[i];
                }
            }
            
            if (time > limit) left = level + 1;
            else {
                right = level;
                answer = Math.min(answer, level);
            }
        }
        
        return answer;
    }
}