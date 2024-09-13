import java.io.*;
import java.util.*;

public class Main {
    static int[] count = new int[100001];
    static int subin;
    static int sister;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());

        System.out.println(bfs(subin));
    }

    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        count[start] = 0;
        while(!queue.isEmpty()) {
            int num = queue.poll();

            // 정답
            if (num == sister) return count[num];

            if (num + 1 <= 100000 && count[num + 1] == 0) {
                queue.add(num + 1);
                count[num + 1] = count[num] + 1;
            }
            if (num - 1 >= 0 && count[num - 1] == 0) {
                queue.add(num - 1);
                count[num - 1] = count[num] + 1;
            }
            if (num * 2 <= 100000 && count[num * 2] == 0) {
                queue.add(num * 2);
                count[num * 2] = count[num] + 1;
            }
        }
        return 0;
    }
}