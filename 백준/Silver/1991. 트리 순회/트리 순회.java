import java.io.*;
import java.util.*;
public class Main {
    static Map<String, String[]> inputs = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String val1 = st.nextToken();
            String val2 = st.nextToken();
            inputs.put(key, new String[]{val1, val2});
        }
        preorder("A");
        sb.append("\n");
        inorder("A");
        sb.append("\n");
        postorder("A");
        System.out.println(sb.toString());
    }

    private static void preorder(String a) {
        sb.append(a);
        if (inputs.get(a)[0].equals(".")) {
            if (!inputs.get(a)[1].equals(".")) {
                preorder(inputs.get(a)[1]);
            }
        }
        else {
            preorder(inputs.get(a)[0]);
            if (!inputs.get(a)[1].equals(".")) {
                preorder(inputs.get(a)[1]);
            }
        }
    }

    private static void inorder(String a) {
        if (!inputs.get(a)[0].equals(".")) {
            inorder(inputs.get(a)[0]);
        }
        if (!inputs.get(a)[1].equals(".")) {
            sb.append(a);
            inorder(inputs.get(a)[1]);
        } else {
            sb.append(a);
        }
    }

    private static void postorder(String a) {
        if (!inputs.get(a)[0].equals(".")) {
            postorder(inputs.get(a)[0]);
        }
        if (!inputs.get(a)[1].equals(".")) {
            postorder(inputs.get(a)[1]);
        }
        sb.append(a);
    }
}