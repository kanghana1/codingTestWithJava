import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        solve(N);
    }

        private static void solve(int n) {
            int[] simpleMap = new int[n + 1];
            parent = new int[n + 1];

            Arrays.fill(simpleMap, Integer.MAX_VALUE);
            simpleMap[1] = 0;

            for (int i = 1 ; i < n ; i++) {
                if (i * 3 <= n) {
                    if (simpleMap[i * 3] > simpleMap[i] + 1) {
                        simpleMap[i * 3] = simpleMap[i] + 1;
                        parent[i * 3] = i;
                    }
                }
                if (i * 2 <= n) {
                    if (simpleMap[i * 2] > simpleMap[i] + 1) {
                        simpleMap[i * 2] = simpleMap[i] + 1;
                        parent[i * 2] = i;
                    }
                }
                if (i + 1 <= n) {
                    if (simpleMap[i + 1] > simpleMap[i] + 1) {
                        simpleMap[i + 1] = simpleMap[i] + 1;
                        parent[i + 1] = i;
                    }
                }
            }

            printAns(simpleMap[n]);
        }

        private static void printAns(int minCnt) {
            StringBuilder sb = new StringBuilder();
            sb.append(minCnt).append("\n");

            int nowIdx = parent.length - 1;
            while (true) {
                sb.append(nowIdx).append(" ");

                if (nowIdx == 1) break;
                nowIdx = parent[nowIdx];
            }
            System.out.println(sb);
        }
    }
