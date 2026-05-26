import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] maps = new int[m][n];
        int INF = 1_000_000_007;
        for (int i = 0; i < m; i++) {
            Arrays.fill(maps[i], INF);
        }
        
        for (int i = 0; i < drops.length; i++) {
            maps[drops[i][0]][drops[i][1]] = i + 1;
        }

        int[][] rowMin = new int[m][n - w + 1];
        for (int i = 0; i < m; i++) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                if (!deque.isEmpty() && deque.peekFirst() <= j - w) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && maps[i][deque.peekLast()] >= maps[i][j]) {
                    deque.pollLast();
                }
                deque.offerLast(j);
                
                if (j >= w - 1) {
                    rowMin[i][j - w + 1] = maps[i][deque.peekFirst()];
                }
            }
        }

        int[][] gridMin = new int[m - h + 1][n - w + 1];

        for (int j = 0; j < n - w + 1; j++) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                if (!deque.isEmpty() && deque.peekFirst() <= i - h) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && rowMin[deque.peekLast()][j] >= rowMin[i][j]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                
                if (i >= h - 1) {
                    gridMin[i - h + 1][j] = rowMin[deque.peekFirst()][j];
                }
            }
        }

        int maxTime = -1;
        int ansR = -1;
        int ansC = -1;
        for (int r = 0; r < m - h + 1; r++) {
            for (int c = 0; c < n - w + 1; c++) {
                int currentTime = gridMin[r][c];

                if (currentTime > maxTime) {
                    maxTime = currentTime;
                    ansR = r;
                    ansC = c;
                }

            }
        }

        return new int[]{ansR, ansC};
    }
}