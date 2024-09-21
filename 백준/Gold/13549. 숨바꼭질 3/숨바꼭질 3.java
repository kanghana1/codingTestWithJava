import java.io.*;
import java.util.*;
public class Main {
    static int[] location = new int[100001];
    static Queue<Integer> queue = new LinkedList<>();
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        location[N] = 1;
        go(N);
        System.out.println(location[K] - 1);
    }

    private static void go(int now) {
        queue.add(now);
        while (!queue.isEmpty()) {
            int pres = queue.poll();
            if (pres == K) break;

            if (range(pres * 2) && location[pres * 2] == 0) {
                location[pres * 2] = location[pres];
                queue.add(pres * 2);
            }
            if (range(pres - 1) && location[pres - 1] == 0) {
                location[pres - 1] = location[pres] + 1;
                queue.add(pres - 1);
            }
            if (range(pres + 1) && location[pres + 1] == 0) {
                location[pres + 1] = location[pres] + 1;
                queue.add(pres + 1);
            }
        }
    }

    private static boolean range(int idx) {
        return idx >= 0 && idx <= 100000;
    }
}
