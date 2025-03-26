import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0 ; i < N ; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a == 0) {
                if (queue.isEmpty()) {
                    sb.append(0);
                } else sb.append(queue.poll());
                sb.append("\n");
            } else {
                queue.add(a);
            }
        }
        System.out.println(sb);
    }
}
