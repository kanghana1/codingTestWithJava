import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    static List<List<Node>> lst = new LinkedList<>();
    static int[] homeToParty;
    static int[] partyToHome;
    static int[][] value;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        value = new int[N + 1][2];
        homeToParty = new int[N + 1];
        partyToHome = new int[N + 1];

        for (int i = 0 ; i <= N ; i++) {
            lst.add(new LinkedList<>());
            homeToParty[i] = Integer.MAX_VALUE;
            partyToHome[i] = Integer.MAX_VALUE;
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            lst.get(start).add(new Node(end, weight));
        }

        /*
        * 1. home -> party
        * 2. party -> home
        * */

        for (int i = 1 ; i <= N ; i++) {
            goParty(i, X); // 1
        }
        goHome(X);

        int max = Integer.MIN_VALUE;
        for (int i = 1 ; i <= N ; i++) {
            int sol = partyToHome[i] + homeToParty[i];
            if (sol > max) max = sol;
        }
        System.out.println(max);
    }

    private static void goParty(int start, int end) {
        Queue<Node> queue = new PriorityQueue<>();
        int[] value = new int[N + 1];
        Arrays.fill(value, Integer.MAX_VALUE);
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            if (nowNode.end == end) {
                homeToParty[start] = min(nowNode.weight, value[end]);
                continue;
            }

            if(value[nowNode.end] < nowNode.weight) continue;

            for (int i = 0 ; i < lst.get(nowNode.end).size() ; i++) {
                Node nextNode = lst.get(nowNode.end).get(i);
                if (value[nowNode.end] + nextNode.weight < value[nextNode.end]) {
                    value[nextNode.end] = nextNode.weight + nowNode.weight;
                    queue.add(new Node(nextNode.end, value[nextNode.end]));
                }
            }
        }
    }

    private static void goHome(int start) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            if(partyToHome[nowNode.end] < nowNode.weight) continue;

            for (int i = 0 ; i < lst.get(nowNode.end).size() ; i++) {
                Node nextNode = lst.get(nowNode.end).get(i);
                if (nowNode.weight + nextNode.weight < partyToHome[nextNode.end]) {
                    partyToHome[nextNode.end] = nextNode.weight + nowNode.weight;
                    queue.add(new Node(nextNode.end, partyToHome[nextNode.end]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int end;
    int weight;

    public Node(int e, int w) {
        this.end = e;
        this.weight = w;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}