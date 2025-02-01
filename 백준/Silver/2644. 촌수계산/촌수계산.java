import java.io.*;
import java.util.*;
public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static boolean[] isVisit;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }
        isVisit = new boolean[N + 1];

        int t = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < t ; i++) {
            st = new StringTokenizer(br.readLine());
            int par = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            lst.get(par).add(child);
            lst.get(child).add(par);
        }
        isVisit[p1] = true;
        dfs(p1, p2, 0);
        if (ans == 0) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void dfs(int now, int goal, int rel) {
        List<Integer> child = lst.get(now);
        for (int ch : child) {
            if (ch == goal) {
                ans = rel + 1;
                return;
            }

            if (!isVisit[ch]) {
                isVisit[ch] = true;
                dfs(ch, goal, rel + 1);
                isVisit[ch] = false;
            }
        }
    }
}
