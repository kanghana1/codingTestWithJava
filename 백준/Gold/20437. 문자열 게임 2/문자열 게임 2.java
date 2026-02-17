import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0 ; t < T ; t++) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<Integer>[] lst = makeList(str);
            solve(lst, K);
        }
        System.out.print(sb);
    }

    private static void solve(List<Integer>[] lst, int k) {
        int minAns = Integer.MAX_VALUE;
        int maxAns = Integer.MIN_VALUE;
        for (int i = 0 ; i < 26 ; i++) {
            if (lst[i].isEmpty() || lst[i].size() < k) continue;
            for (int j = k - 1 ; j < lst[i].size() ; j++) {
                int now = lst[i].get(j) - lst[i].get(j - k + 1);
                minAns = Math.min(minAns, now + 1);
                maxAns = Math.max(maxAns, now + 1);
            }
        }

        if (maxAns == Integer.MIN_VALUE || minAns == Integer.MAX_VALUE) {
            sb.append(-1 + "\n");
            return;
        }
        sb.append(minAns + " " + maxAns + "\n");
    }


    private static List<Integer>[] makeList(String str) {
        List<Integer>[] alphabets = new ArrayList[26];

        for (int i = 0 ; i < 26 ; i++) {
            alphabets[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < str.length() ; i++) {
            alphabets[str.charAt(i) - 'a'].add(i);
        }

        return alphabets;
    }
}
