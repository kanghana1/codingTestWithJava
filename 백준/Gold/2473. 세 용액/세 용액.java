import java.io.*;
import java.util.*;
public class Main {
    static long[] liq;
    static long[][] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        liq = new long[N];
        res = new long[N][4];
        for (int i = 0 ; i < liq.length ; i++) {
            liq[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(liq);
        for (int i = 0 ; i < liq.length ; i++) {
            func(i);
        }

        Arrays.sort(res, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return (int) (o1[0] - o2[0]);
            }
        });

        System.out.println(res[0][1] + " " + res[0][2] + " " + res[0][3]);
    }

    private static void func(int i) {
        long goal = liq[i]; // 목표
        int startIdx = 0;
        int endIdx = liq.length - 1;

        long ans = Long.MAX_VALUE;
        int ansLeft = -1;
        int ansRight = -1;

        while (startIdx < endIdx) {
            if (startIdx == i || endIdx == i) {
                if (startIdx == i) startIdx++;
                else endIdx--;
                continue;
            }
            if (Math.abs(liq[startIdx] + liq[endIdx] + goal) < ans) {
                ans = Math.abs(liq[startIdx] + liq[endIdx] + goal);
                ansLeft = startIdx;
                ansRight = endIdx;
            }

            if (liq[startIdx] + liq[endIdx] + goal > 0) endIdx--;
            else if (liq[startIdx] + liq[endIdx] + goal < 0) startIdx++;
            else break;
        }
        ans(ansLeft, ansRight, i);
    }

    private static void ans(int ansLeft, int ansRight, int i) {
        res[i][0] = Math.abs(liq[ansLeft] + liq[ansRight] + liq[i]);
        if (ansLeft < i) {
            if (ansRight > i) {
                res[i][1] = liq[ansLeft];
                res[i][2] = liq[i];
                res[i][3] = liq[ansRight];
            } else {
                res[i][1] = liq[ansLeft];
                res[i][2] = liq[ansRight];
                res[i][3] = liq[i];
            }
        } else {
            res[i][1] = liq[i];
            res[i][2] = liq[ansLeft];
            res[i][3] = liq[ansRight];
        }
    }
}