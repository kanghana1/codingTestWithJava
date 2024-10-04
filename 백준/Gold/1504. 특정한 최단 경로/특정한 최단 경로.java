import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static List<List<E>> lst = new LinkedList<>();
    static boolean[] isVisit;
    static int[] dist;
    static final int MAX = 200000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        isVisit = new boolean[N + 1];
        dist = new int[N + 1];
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new LinkedList<>());
        }

        for (int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            lst.get(start).add(new E(end, distance));
            lst.get(end).add(new E(start, distance));// 양방향이니까
        }
        st = new StringTokenizer(br.readLine());
        int nec1 = Integer.parseInt(st.nextToken());
        int nec2 = Integer.parseInt(st.nextToken());

        // start -> nec1 -> nec2 -> end 혹은 start -> nec2 -> nec1 -> end 이므로 끊어서 구하기
        int sol1 = 0;
        sol1 += find(1, nec1);
        sol1 += find(nec1, nec2);
        sol1 += find(nec2, N);

        int sol2 = 0;
        sol2 += find(1, nec2);
        sol2 += find(nec2, nec1);
        sol2 += find(nec1, N);

        int ans = 0;
        if (sol1 >= MAX && sol2 >= MAX) ans = -1;
        else ans = Math.min(sol1, sol2);
        System.out.println(ans);
    }

    private static int find(int start, int end) {
        Arrays.fill(dist, MAX);
        Arrays.fill(isVisit, false);

        PriorityQueue<E> queue = new PriorityQueue<>();
        queue.add(new E(start, 0));
        dist[start] = 0;
        while (!queue.isEmpty()) {
            E nowNode = queue.poll();
            int location = nowNode.end;

            if (!isVisit[location]) {
                isVisit[location] = true;

                for (E node : lst.get(location)) {
                    if (!isVisit[node.end] && dist[node.end] > dist[location] + node.distance) {
                        dist[node.end] = dist[location] + node.distance;
                        queue.add(new E(node.end, dist[node.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
class E implements Comparable<E>{
    int end;
    int distance;

    public E(int e, int d) {
        this.end = e;
        this.distance = d;
    }

    @Override
    public int compareTo(E o) {
        return distance - o.distance;
    }
}