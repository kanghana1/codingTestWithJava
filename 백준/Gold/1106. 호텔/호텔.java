import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int C;
    static int rotate;
    static List<int[]> lst = new ArrayList<>(); // 인원, 가격
    public static void main(String[] args) throws IOException {
        inputFunc();

        int[] dp = new int[rotate + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;

        for (int i = 1 ; i <= rotate ; i++) {
            for (int j = 0 ; j < lst.size() ; j++) {
                int num = lst.get(j)[0];
                int price = lst.get(j)[1];
                if (num > i) continue;

                dp[i] = Math.min(dp[i], dp[i - num] + price);
            }
        }

        int ans = dp[C];
        for (int i = C ; i <= rotate ; i++) {
            ans = Math.min(ans, dp[i]);
        }
        System.out.println(ans);
    }

    private static void inputFunc() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int maxCustomer = 0;
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            maxCustomer = Math.max(maxCustomer, customer);
            lst.add(new int[]{customer, price});
        }

        rotate = maxCustomer + C;
    }
}
