import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        long maxSize = 200000001;
        boolean[][] twoSum = new boolean[10000][20000];
        for (int i = 0 ; i < N ; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);
        int max = nums[N - 1];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                int sum = nums[i] + nums[j];
                if (sum > max) continue;
                twoSum[sum / 20000][sum % 20000] = true;
            }
        }

        int ans = 0;
        for (int i = N - 1 ; i >= 0 ; i--) {
            int goal = nums[i];
            for (int j = 0 ; j <= i ; j++) {
                if (twoSum[(goal - nums[j]) / 20000][(goal - nums[j]) % 20000]) {
                    ans = goal;
                    break;
                }
            }
            if (ans != 0) break;
        }
        System.out.println(ans);
    }
}
