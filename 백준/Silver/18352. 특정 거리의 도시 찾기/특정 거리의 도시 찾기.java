import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int dis = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0 ; i < edge ; i++) {
            st = new StringTokenizer(br.readLine());
           int s = Integer.parseInt(st.nextToken());
           int e = Integer.parseInt(st.nextToken());
           lst.get(s).add(e);
        }

        if (lst.get(start).isEmpty()) {
            System.out.println(-1);
            return;
        }
        findShort(start, dis);
        System.out.println(sb);
    }

    private static void findShort(int start, int dis) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int[] dist = new int[N + 1];

        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> child = lst.get(node);
            if (child.isEmpty()) {
                continue;
            }
            for (int ch : child) {
                if (dist[ch] == 0) {
                    dist[ch] = dist[node] + 1;
                    queue.add(ch);
                }
            }
        }

        boolean exist = false;
        dist[start] = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (dist[i] == dis) {
                sb.append(i).append("\n");
                exist = true;
            }
        }
        if (!exist) sb.append(-1);
    }
}
