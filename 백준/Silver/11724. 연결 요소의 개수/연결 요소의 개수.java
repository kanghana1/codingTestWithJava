import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static List<Integer>[] mainLst;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int cnt = 0;
        mainLst = new ArrayList[n];
        isVisit = new boolean[n];

        for (int i = 0 ; i < n ; i++) {
            mainLst[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < m ; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            mainLst[idx - 1].add(val);
            mainLst[val - 1].add(idx);
        }

        for (int i = 0 ; i < n ; i++) {
            if (!isVisit[i]) {
                dfs(i + 1);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    // val - 1 = idx;
    public static void dfs(int val) {
        isVisit[val - 1] = true;
        for (int i = 0 ; i < mainLst[val - 1].size() ; i++) {
            int nextVal = mainLst[val - 1].get(i);
            if (!isVisit[nextVal - 1]) {
                dfs(nextVal);
            }
        }
    }
}
