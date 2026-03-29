import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int[] arr;
    static int cnt = 0;
    static int goal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findAns(0, 0, 0);


        System.out.println(cnt);
    }

    private static void findAns(int idx, int sum, int numCnt) {
        if (idx == arr.length) {
            if (sum == goal && numCnt > 0) cnt++;
            return;
        }

        findAns(idx + 1, sum + arr[idx], numCnt + 1);
        findAns(idx + 1, sum, numCnt);
    }
}
