import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Node> lst = new ArrayList<>();
        long[][] input = new long[N][2];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long home = Long.parseLong(st.nextToken());
            long work = Long.parseLong(st.nextToken());
            input[i][0] = min(home, work);
            input[i][1] = max(home, work);
        }
        long dis = Long.parseLong(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            if (input[i][1] - input[i][0] <= dis) lst.add(new Node(input[i][0], input[i][1]));
        }
        if (lst.isEmpty()) {
            System.out.println(0);
            return;
        }
        Collections.sort(lst, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o1.work - o2.work);
            }
        });
        long max = 0;
        Queue<Long> queue = new PriorityQueue<>();
        for (int i = 0 ; i < lst.size() ; i++) {
            long end = lst.get(i).work;
            long start = end - dis;
            while (!queue.isEmpty() && queue.peek() < start) {
                queue.poll();
            }
            queue.add(lst.get(i).home);
            max = max(max, queue.size());
        }
        System.out.println(max);
    }
}
class Node {
    long home;
    long work;
    Node(long h, long w) {
        this.home = h;
        this.work = w;
    }
}
