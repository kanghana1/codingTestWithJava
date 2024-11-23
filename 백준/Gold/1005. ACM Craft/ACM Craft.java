import java.io.*;
import java.util.*;
public class Main {
    static boolean[] isVisit;
    static int[] price;
    static int buildingCnt;
    static List<List<Integer>> lst = new ArrayList<>();
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < T ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildingCnt = Integer.parseInt(st.nextToken());
            int ruleCnt = Integer.parseInt(st.nextToken());
            isVisit = new boolean[buildingCnt + 1];
            dp = new int[buildingCnt + 1];

            for (int j = 0 ; j <= buildingCnt ; j++) {
                lst.add(new ArrayList<>());
                dp[j] = -1;
            }

            st = new StringTokenizer(br.readLine());
            price = new int[buildingCnt + 1];
            for (int j = 1 ; j <= buildingCnt ; j++) {
                price[j] = Integer.parseInt(st.nextToken());

            }
            for (int j = 0 ; j < ruleCnt ; j++) {
                st = new StringTokenizer(br.readLine());
                int front = Integer.parseInt(st.nextToken());
                int back = Integer.parseInt(st.nextToken());
                lst.get(back).add(front);
            }

            int goal = Integer.parseInt(br.readLine());
            // 입력 끝
            dp();
            sb.append(dp[goal]).append("\n");
            lst.clear();
        }
        System.out.println(sb.toString());
    }

    private static void dp() {
        for (int i = 1 ; i <= buildingCnt ; i++) {
            if (lst.get(i).isEmpty()) dp[i] = price[i];
            else {
                price(i);
            }
        }
    }

    private static void price(int i) {
        List<Integer> fst = lst.get(i);
        if (lst.get(i).isEmpty()) {
            dp[i] = price[i];
            return;
        }
        for (int j = 0 ; j < fst.size() ; j++) {
            if (dp[fst.get(j)] == -1) price(fst.get(j));
            dp[i] = Math.max(dp[i], dp[fst.get(j)] + price[i]);
        }
    }
}