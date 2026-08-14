class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        for (int i = 1; i <= n - 4; i++) {
            for (int j = i + 1; j <= n - 3; j++) {
                for (int k = j + 1; k <= n - 2; k++) {
                    for (int l = k + 1; l <= n - 1; l++) {
                        for (int p = l + 1; p <= n; p++) {
                            if (isValid(i, j, k, l, p, q, ans)) {
                                answer++;
                            }
                            
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    private static boolean isValid(int num1, int num2, int num3, int num4, int num5, int[][] cases, int[] ans) {
        for (int i = 0 ; i < cases.length ; i++) {
            int cnt = 0;
            for (int a : cases[i]) {
                if (a == num1 || a == num2 || a == num3 || a == num4 || a == num5) cnt++;
            }
            
            if (cnt != ans[i]) return false;
        }
        return true;
    }
}