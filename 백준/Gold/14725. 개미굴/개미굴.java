import java.io.*;
import java.util.*;
public class Main {
    static final String SPACE = "--";
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node node = new Node("root");

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int depth = Integer.parseInt(st.nextToken());
            Node current = node;
            for (int j = 0; j < depth; j++) {
                String name = st.nextToken();
                current.addChild(name);
                current = current.children.stream()
                        .filter(child -> child.value.equals(name))
                        .findFirst().get();
            }
        }
        dfs(node,-1);
        System.out.println(sb);
    }

    private static void dfs(Node node, int floor) {
        if (!node.value.equals("root")) {
            for (int i = 0 ; i < floor ; i++) {
                sb.append(SPACE);
            }
            sb.append(node.value + "\n");
        }


        Collections.sort(node.children);
        for (Node ch : node.children) {
            dfs(ch, floor + 1);
        }
    }
}
class Node implements Comparable<Node> {
    String value;
    List<Node> children = new ArrayList<>();

    public Node(String n) {
        this.value = n;
    }

    void addChild(String str) {
        for (Node child : children) {
            if (str.equals(child.value)) return;
        }
        children.add(new Node(str));
    }

    @Override
    public int compareTo(Node o) {
        return this.value.compareTo(o.value);
    }
}