class Solution {
    public int solution(int[] players, int m, int k) {
        int[] serverCnt = new int[24];
        int answer = 0;
        
        for (int i = 0 ; i < players.length ; i++) {
            int need = players[i] / m;
            if (need > serverCnt[i]) {
                int upCnt = need - serverCnt[i];
                answer += upCnt;
                for (int j = i ; j < i + k ; j++) {
                    if (j >= serverCnt.length) break;
                    serverCnt[j] += upCnt;
                }
            }
        }
        
        
        return answer;
    }
}