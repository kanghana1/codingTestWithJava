import java.io.*;
import java.util.*;
// 9466
public class Main {
    static List<List<Integer>> choose = new ArrayList<>();
    static List<Integer> member = new ArrayList<>();
    static boolean[] include;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0 ; t < T ; t++) {
            cnt = 0;
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            include = new boolean[N + 1];
            for (int i = 0 ; i <= N ; i++) {
                choose.add(new ArrayList<>());
            }
            for (int i = 1 ; i <= N ; i++) {
                choose.get(Integer.parseInt(st.nextToken())).add(i);
            }
            for (int i = 1 ; i <= N ; i++) {
                if (!include[i] && !choose.get(i).isEmpty()) {
                    dfs(i, i, true);
                    if (!member.isEmpty()){
                        // success
                        cnt += member.size();
                        member.clear();
                    }
                }
            }
            sb.append(N - cnt).append("\n");
            choose.clear();
        }
        System.out.println(sb);
    }

    private static boolean dfs(int n, int goal, boolean first) {
        List<Integer> lst = choose.get(n);
        member.add(n);
        include[n] = true;
        if (lst.isEmpty()) {
            member.clear();
            return false;
        }
        for (int i = 0 ; i < lst.size() ; i++) {
            if (lst.get(i) == goal) {
                return true;
            }
            else if (!include[lst.get(i)] && !choose.get(lst.get(i)).isEmpty()) {
                boolean dfs = dfs(lst.get(i), goal, false);
                if (member.size() > 1 && dfs) {
                    return true;
                }
            }
        }
        if (first) member.clear();
        else member.remove((Integer)n);
        return false;
    }
}