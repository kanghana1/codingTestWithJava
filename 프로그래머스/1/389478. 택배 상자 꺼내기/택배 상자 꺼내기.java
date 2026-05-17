import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int h = n / w + 1;
        
        int x = 0;
        int[][] arrs = new int[h][w];
        int base = 1;
        for (int i = 0 ; i < h ; i++) {
            int y = h - 1 - i;
            if (i % 2 == 0) {
                for (int j = 0 ; j < w ; j++) {
                    if (base == num) x = j;
                    arrs[y][j] = base++;
                    if (base > n) break;
                }
            } else {
                for (int j = w - 1 ; j >= 0 ; j--) {
                    if (base == num) x = j;
                    arrs[y][j] = base++;
                    if (base > n) break;
                }
            }
            if (base > n) break;
        }

        for (int i = 0 ; i < h ; i++) {
            // System.out.print(arr[i][x] + " -> " + answer + "\n");
            if (arrs[i][x] != 0) answer++; 
            if (arrs[i][x] == num) break;
        }
        
        return answer;
    }
    
}