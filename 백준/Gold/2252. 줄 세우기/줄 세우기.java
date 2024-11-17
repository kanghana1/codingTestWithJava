import java.io.*;
import java.util.*;
public class Main {

    static List<List<Integer>> lst = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }
        for (int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lst.get(a).add(b); // 자식저장
            parent[b]++;
        }
        for (int i = 1 ; i <= N ; i++) {
            if (parent[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            sb.append(node + " ");
            List<Integer> child = lst.get(node);
            for (int i = 0 ; i < child.size() ; i++) {
                int ch = child.get(i);
                parent[ch]--;
                if (parent[ch] == 0) queue.add(ch);
            }
        }
        System.out.println(sb.toString());
    }
}