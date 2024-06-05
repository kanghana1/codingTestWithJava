import java.util.*;
import java.io.*;
public class Main {
    static ArrayList<Integer>[] store;
    static int n;
    static boolean[] isVisitDfs; // 여기서 = new isVisitDfs[n] 으로 하면 n이 0으로 초기화 된 상태에서 생성되기 때문에 n을 받은 후 main 안에서 배열 생성해야함
    static boolean[] isVisitBfs;
    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // number of node
        int m = Integer.parseInt(st.nextToken()); // number of line
        int start = Integer.parseInt(st.nextToken()); // start number

        store = new ArrayList[n]; // arraylist배열 생성
        isVisitDfs = new boolean[n];
        isVisitBfs = new boolean[n];

        for (int i = 0 ; i < n ; i++) {
            store[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            store[parent - 1].add(child);
            store[child - 1].add(parent);
        }
        for (int i = 0 ; i < n ; i++) {
            Collections.sort(store[i]);
        }
        dfs(start);
        sb.append("\n");
        bfs(start);
        System.out.println(sb.toString());

        
    }

    public static void dfs(int s) {
        isVisitDfs[s - 1] = true;
        sb.append(s).append(" ");
        for (int i : store[s - 1]) {
            if (!isVisitDfs[i - 1]) {
                dfs(i);
            }
        }
    } 



    public static void bfs(int s) {
        isVisitBfs[s - 1] = true;
        queue.add(s);
        while(!queue.isEmpty()) {
            for (int i : store[queue.peek() - 1]) {
                if (!isVisitBfs[i - 1]) {
                    queue.add(i);
                    isVisitBfs[i - 1] = true; 
                }
            }
            sb.append(queue.peek()).append(" ");
            queue.poll();
        }
    } 
}
