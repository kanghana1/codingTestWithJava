import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int kind = Integer.parseInt(st.nextToken());
        int eatCnt = Integer.parseInt(st.nextToken());
        int coupon = Integer.parseInt(st.nextToken());

        int[] input = new int[N + eatCnt - 1];
        int[] sushi = new int[kind + 1];
        for (int i = 0 ; i < N ; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N ; i < input.length ; i++) {
            input[i] = input[i % N];
        }


        int cnt = 0;
        for (int i = 0 ; i < eatCnt ; i++) {
            if (sushi[input[i]] == 0) cnt++;
            sushi[input[i]]++;
        }
        int max = cnt;
        if (sushi[coupon] == 0) max += 1;

        for (int i = eatCnt ; i < input.length ; i++) {
            int left = i - eatCnt;
            if (sushi[input[left]] == 1) {
                cnt--;
            }
            sushi[input[left]]--;


            if (sushi[input[i]] == 0) cnt++;
            sushi[input[i]]++;


            if (sushi[coupon] == 0) max = Math.max(max, cnt + 1);
            else max = Math.max(max, cnt);
        }
        System.out.println(max);

    }
}