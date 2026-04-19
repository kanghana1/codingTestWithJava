import java.util.*;
class Solution {
    public int solution(int[] nums) {
        boolean[] exists = new boolean[200001];
        int cnt = 0;
        int maxCnt = nums.length / 2;
        for (int n : nums) {
            if (!exists[n]) {
                exists[n] = true;
                cnt++;
            }
        }
        
        if (maxCnt <= cnt) return maxCnt;
        return cnt;
    }
}