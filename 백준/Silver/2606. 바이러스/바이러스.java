import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] lst;
    static boolean[] isVisit;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        lst = new ArrayList[n];
        isVisit = new boolean[n];

        for (int i = 0 ; i < n ; i++) {
            lst[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            lst[idx - 1].add(val);
            lst[val - 1].add(idx);
        }
        dfs(1);
        System.out.println(count - 1);
    }

    public static void dfs(int start) {
        isVisit[start - 1] = true;
        count++;
        for (int i = 0 ; i < lst[start - 1].size() ; i++) {
            if (!isVisit[lst[start - 1].get(i) - 1]) {
                dfs(lst[start - 1].get(i));
            }
        }
    }
}
