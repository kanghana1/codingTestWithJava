import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Integer> lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        while (str != null && !str.isEmpty()) {
             lst.add(Integer.valueOf(str));
             str = br.readLine();
        }
        Node root = new Node(lst.get(0));
        for (int i = 1 ; i < lst.size() ; i++) {
            add(root, lst.get(i));
        }

        Node node = root;
        print(root);
    }

    private static void add(Node node, int value) {
        if (value < node.key) {
            if (node.left == null) node.left = new Node(value);
            else add(node.left, value);
        } else {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                add(node.right, value);
            }
        }
    }
    private static void print(Node node) {
        if (node.left != null) print(node.left);
        if (node.right != null) print(node.right);
        System.out.println(node.key);
    }
}
class Node {
    int key;
    Node left;
    Node right;
    Node(int key) {
        this.key = key;
    }
}