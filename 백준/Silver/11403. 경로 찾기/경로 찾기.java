import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] lst;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        lst = new ArrayList[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            lst[i] = new ArrayList<>();
        }


        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) lst[j].add(i);
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            explore(i);
        }

        int[][] result = new int[N][N];
        for (int i = 1 ; i <= N ; i++) {
            for (int j = 0 ; j < lst[i].size() ; j++) {
                result[i - 1][lst[i].get(j) - 1] = 1;
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                sb.append(result[j][i] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void explore(int n) {
        boolean[] isVisited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < lst[n].size() ; i++) {
            int a = lst[n].get(i);
            isVisited[a] = true;
            queue.add(a);
        }

        while (!queue.isEmpty()) {
            int a = queue.poll();
            for (int i = 0 ; i < lst[a].size() ; i++) {
                if (!isVisited[lst[a].get(i)]) {
                    isVisited[lst[a].get(i)] = true;
                    lst[n].add(lst[a].get(i));

                    queue.add(lst[a].get(i));
                }
            }
        }
    }
}