import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static boolean[] isVisit;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i <= N ; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lst.get(b).add(a);
        }
        int[] count = new int[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            if (lst.get(i).isEmpty()) continue;
            isVisit = new boolean[N + 1];
            dfs(i);
            int cnt = 0;
            for (boolean visit : isVisit) {
                if (visit) cnt++;
            }
            max = Math.max(cnt, max);
            count[i] = cnt;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= N ; i++) {
            if (count[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int now) {
        isVisit[now] = true;
        List<Integer> canHacks = lst.get(now);
        if (canHacks.isEmpty()) {
            return;
        }
        for (int ob : canHacks) {
            if (!isVisit[ob]) {
                dfs(ob);
            }
        }
    }
}