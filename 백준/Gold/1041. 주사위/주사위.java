import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];
        for (int i = 0 ; i < 6 ; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            long sum = 0;
            long max = 0;
            for (int i = 0 ; i < 6 ; i++) {
                sum += dice[i];
                max = max(max, dice[i]);
            }
            System.out.println(sum - max);
            return;
        }
        long ans = minSum(N, dice);
        System.out.println(ans);
    }

    public static long minSum(int N, int[] dice) {
        long ans = 0;
        long sumOne = Long.MAX_VALUE;
        long sumTwo = Long.MAX_VALUE;
        long sumThree = 0;

        for (int i = 0 ; i < 6 ; i++) {
            sumOne = min(sumOne, dice[i]);
            for (int j = i + 1 ; j < 6 ; j++) {
                if (i + j != 5) {
                    sumTwo = min(sumTwo, dice[i] + dice[j]);
                }
            }
            if (i <= 2) {
                sumThree += min(dice[i], dice[5 - i]);
            }
        }

        ans += (useOneBlock(N) * sumOne);
        ans += useTwoBlock(N) * sumTwo;
        ans += useThreeBlock(N) * sumThree;
        return ans;
    }
    public static int useThreeBlock(int N) {
        return 4;
    }

    public static long useTwoBlock(long N) {
        return 8 * (N - 2) + 4;
    }

    public static long useOneBlock(long N) {
        return 5 * (N - 2) * (N - 2) + 4 * (N - 2);
    }
}
