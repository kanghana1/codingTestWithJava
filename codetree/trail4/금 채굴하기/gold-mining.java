import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int gold = 0;
        int[][] ground = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) gold++;
                ground[i][j] = a;
            }
        }

        int budget = gold * M;

        int ans = 0;
        int goldCnt = 0;
        int K = 0;
        while (true) {
            int cost = K * K + (K + 1) * (K + 1); 
            if (cost > budget || ans == gold) break;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    goldCnt = 0;
                    for (int dr = -K; dr <= K; dr++) {
                        for (int dc = -K; dc <= K; dc++) {
                            if (Math.abs(dr) + Math.abs(dc) <= K) {
                                int nr = r + dr;
                                int nc = c + dc;
                            
                                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                    if (ground[nr][nc] == 1) {
                                        goldCnt++;
                                    }
                                }
                            }
                        }
                    }
                    
                    if (goldCnt * M >= cost) {
                        ans = Math.max(ans, goldCnt);
                    }
                }
            }
            K++;
        }

        System.out.print(ans);

    }
}