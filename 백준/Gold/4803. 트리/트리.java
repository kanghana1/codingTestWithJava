import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static boolean[] isVisit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            if (node == 0 && edge == 0) break;

            isVisit = new boolean[node + 1];
            for (int i = 0 ; i <= node ; i++) {
                lst.add(new ArrayList<>());
            }
            for (int i = 0 ; i < edge ; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                lst.get(start).add(end);
                lst.get(end).add(start);
            }
            int cnt = 0;
            for (int i = 1 ; i <= node ; i++) {
                if (!isVisit[i]) {
                    cnt += find(i);
                }
            }
            sb.append("Case " + testcase + ": ");
            if (cnt == 0) sb.append("No trees.\n");
            else if (cnt == 1) sb.append("There is one tree.\n");
            else sb.append("A forest of " + cnt + " trees.\n");
            testcase++;
            lst.clear();
        }
        System.out.println(sb);

    }

    private static int find(int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, -1});
        isVisit[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int parent = current[1];

            for (int neighbor : lst.get(node)) {
                if (!isVisit[neighbor]) {
                    queue.add(new int[]{neighbor, node});
                    isVisit[neighbor] = true;
                } else if (neighbor != parent) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
