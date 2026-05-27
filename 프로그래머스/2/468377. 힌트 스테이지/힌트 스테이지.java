import java.util.*;
class Solution {
    static int answer = Integer.MAX_VALUE;
    public int solution(int[][] cost, int[][] hint) {
        int[] hintCnt = new int[cost.length + 1]; // 구매한 힌트권 개수
        
        
        dfs(0, 0, hintCnt, cost, hint);
        
        return answer;
    }
    
    public static void dfs(int stage, int price, int[] hintCnt, int[][] cost, int[][] hint) {
        int p = price;
        if (stage == cost.length) {
            answer = Math.min(answer, price);
            return;
        }
        int availableHint = hintCnt[stage + 1];
        
        int cnt = availableHint > cost.length - 1 ? cost.length - 1 : hintCnt[stage + 1];
        
        // 마지막 스테이지는 힌트구매 불가
        if (stage == cost.length - 1) {
            dfs(stage + 1, p + cost[stage][cnt], hintCnt, cost, hint);
            return;
        }
        
        // 힌트 구매
        for (int j = 1 ; j < hint[stage].length ; j++) {
            hintCnt[hint[stage][j]]++;
        }
        
        
        dfs(stage + 1, p + cost[stage][cnt] + hint[stage][0], hintCnt, cost, hint);
        for (int j = 1 ; j < hint[stage].length ; j++) {
            hintCnt[hint[stage][j]]--;
        }
        
        // 힌트 구매 X
        dfs(stage + 1, p + cost[stage][cnt], hintCnt, cost, hint);
    }
}