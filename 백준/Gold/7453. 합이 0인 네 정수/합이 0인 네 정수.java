import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] inputs = new int[N][4];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputs[i][0] = Integer.parseInt(st.nextToken());
            inputs[i][1] = Integer.parseInt(st.nextToken());
            inputs[i][2] = Integer.parseInt(st.nextToken());
            inputs[i][3] = Integer.parseInt(st.nextToken());
        }
        /*
         * 단순히 contains 쓰면 조건에 맞는 합의 개수를 알 수 없음 -> upperBound & LowerBound 사용
         * */
        long[] sumAB = new long[N * N];
        long[] sumCD = new long[N * N]; // 정렬해서 upper & LowerBound
        int idx = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                sumAB[idx] = inputs[i][0] + inputs[j][1];
                sumCD[idx] = inputs[i][2] + inputs[j][3];
                idx++;
            }
        }
        Arrays.sort(sumCD);

        long ans = 0;
        for (int i = 0 ; i < sumAB.length ; i++) {
            int upperBoundIdx = upperBound(-1 * sumAB[i], sumCD);
            int lowerBoundIdx = lowerBound(-1 * sumAB[i], sumCD);
            if (lowerBoundIdx <= upperBoundIdx) ans += (upperBoundIdx - lowerBoundIdx);
        }
        System.out.println(ans);
    }

    private static int lowerBound(long goal, long[] sumCD) {
        int low = 0;
        int high = sumCD.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (sumCD[mid] >= goal) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private static int upperBound(long goal, long[] sumCD) {
        int low = 0;
        int high = sumCD.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (sumCD[mid] <= goal) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}