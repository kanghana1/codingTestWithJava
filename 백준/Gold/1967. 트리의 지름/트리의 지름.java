import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class Main {
    static List<List<Node>> lst = new LinkedList<>();
    static int[] weight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        weight = new int[N + 1];
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new LinkedList<>());
        }

        for (int i = 0 ; i < N - 1 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            lst.get(parent).add(new Node(child, w));
        }

        dfs(1);
        int max = 0;
        for (int i = 1 ; i <= N ; i++) {
            if (weight[i] > max) max = weight[i];
        }
        System.out.println(max);
    }

    private static int dfs(int start) {
        List<Node> list = lst.get(start);
        if (list.size() == 0) return 0;
        int[] children = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i++) {
            int w = list.get(i).weight + dfs(list.get(i).child);
            children[i] = w;
        }
        Arrays.sort(children);
        weight[start] = list.size() >= 2 ? children[children.length - 1] + children[children.length - 2] : children[children.length - 1];
        return children[children.length - 1];
    }
}
class Node {
    int child;
    int weight;

    public Node(int c, int w) {
        this.child = c;
        this.weight = w;
    }
}