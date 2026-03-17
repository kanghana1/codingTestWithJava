import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class Main {
    static Set<Integer> set = new TreeSet<>();
    static boolean[] isVisit;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        num = new int[N + 1];
        for (int i = 1 ; i <= N ; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        int maxNum = 0;

        for (int i = 1 ; i <= N ; i++) {
            isVisit = new boolean[N + 1];
            int ans = dfs(i, i);
            if (ans == 1) {
                set.add(i);
            }
        }

        ansPrint();
    }

    private static void ansPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(set.size()).append("\n");
        for (int i : set) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }


    private static int dfs(int start, int ob) {
        int now = num[ob];
        isVisit[ob] = true;
        if (now == start) {
            return 1;
        }

        if (!isVisit[now]) {
            return dfs(start, now);
        }

        return -1;
    }
}
