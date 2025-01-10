import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] input = new Node[N];
        int[] dp = new int[N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            input[i] = new Node(start, end);
        }
        Arrays.sort(input, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.start - o2.start;
            }
        });

        for (int i = 0 ; i < N ; i++) {
            dp[i] = 1;

            for (int j = 0 ; j < i ; j++) {
                if (input[i].end > input[j].end) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int max = 0;
        for (int i = 0 ; i < N ; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);
    }
}

class Node {
    int start;
    int end;
    public Node(int s, int e) {
        this.start = s;
        this.end = e;
    }
}