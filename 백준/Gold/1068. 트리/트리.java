import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> trees = new HashMap<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        parent = new int[N];
        for (int i = 0 ; i < N ; i++) {
            trees.put(i, new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            int node = Integer.parseInt(st.nextToken());
            parent[i] = node;
            if (node == -1) continue;
            trees.get(node).add(i);
        }

        int removeNode = Integer.parseInt(br.readLine());
        remove(removeNode, true);

        int cnt = 0;
        Set<Integer> parents = trees.keySet();

        for (int key : parents) {
            if (trees.get(key).isEmpty()) cnt++;
        }
        System.out.println(cnt);
    }

    private static void remove(int node, boolean init) {
        List<Integer> integers = trees.get(node);
        if (integers.isEmpty()) {
            trees.remove(node);
            if (init) {
                trees.get(parent[node]).remove((Integer) node);
            }
            return;
        }

        for (int ch : integers) {
            remove(ch, false);
        }
        trees.remove(node);
    }
}