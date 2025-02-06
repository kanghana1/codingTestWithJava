import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Node> lst = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            lst.add(new Node(x, y));
        }
        Collections.sort(lst, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o2.y - o1.y);
            }
        });

        long length = 0;
        Queue<Long> queue = new PriorityQueue<>();
        for (int i = 0 ; i < lst.size() ; i++) {
            long end = lst.get(i).y;
            long start = lst.get(i).x;
            if (!queue.isEmpty() && queue.peek() > end) {
                length += lst.get(i - queue.size()).y - queue.peek();
                queue.clear();
            }
            queue.add(start);
        }
        length += lst.get(lst.size() - queue.size()).y - queue.peek();
        System.out.println(length);
    }
}
class Node {
    long x;
    long y;
    Node(long x, long y) {
        this.x = x;
        this.y = y;
    }
}