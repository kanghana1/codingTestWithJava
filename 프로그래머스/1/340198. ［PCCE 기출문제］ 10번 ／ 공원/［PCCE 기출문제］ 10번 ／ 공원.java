import java.util.*;
class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        Arrays.sort(mats);
        
        for (int i = 0 ; i < park.length ; i++) {
            for (int j = 0 ; j < park[i].length ; j++) {
                if (park[i][j].equals("-1")) {
                    int ans = find(i, j, mats, park);
                    answer = Math.max(answer, ans);
                }
            }
        }
        
        
        return answer;
    }
    
    private static boolean canGo(int y, int x, int a, int b) {
        return y >= 0 && y < a && x >= 0 && x < b;
    }
    
    private static int find(int y, int x, int[] mats, String[][] park) {
        int ans = -1;
        for (int k = 0 ; k < mats.length ; k++) {
            int size = mats[k];
            boolean canMake = true;
            for (int i = y ; i < y + size ; i++) {
                for (int j = x ; j < x + size ; j++) {
                    if (!canGo(i, j, park.length, park[0].length) || !park[i][j].equals("-1")) {
                        canMake = false;
                        break;
                    }
                }
                
                if (!canMake) break;
            }
            if (!canMake) break;
            ans = size;
        }
        return ans;
    }
}