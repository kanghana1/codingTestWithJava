import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = Integer.MAX_VALUE;
        int[][] memo = new int[info.length + 1][m];
        for ( int i = 0; i <= info.length; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        
        memo[0][0] = 0;
        for (int i = 1 ; i <= info.length ; i++) {
            int costA = info[i - 1][0];
            int costB = info[i - 1][1];
            
            for (int j = 0 ; j < m ; j++) {
                if (memo[i - 1][j] == Integer.MAX_VALUE) continue;
                memo[i][j] = Math.min(memo[i][j], memo[i - 1][j] + costA);
                if (j + costB < m) {
                    memo[i][j + costB] = Math.min(memo[i][j + costB], memo[i - 1][j]);
                }
            }
        }
        
        for (int j = 0; j < m; j++) {
            if (memo[info.length][j] < n) {
                answer = Math.min(answer, memo[info.length][j]);
            }
        }
        
        if (answer == Integer.MAX_VALUE) answer = -1;
        
        return answer;
    }

}