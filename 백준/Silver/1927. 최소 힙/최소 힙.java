import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue<Integer> queue = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            int a = Integer.parseInt(br.readLine());
            if (a == 0) {
                if (queue.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(queue.remove()).append("\n");
                }

            } else {
                queue.add(a);
            }
        }
        System.out.println(sb.toString());
    }
}