import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int cnt = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0 ; i < cnt ; i++) {
            sum += inputs[i];
        }
        int max = sum;
        for (int i = cnt ; i < N ; i++) {
            sum -= inputs[i - cnt];
            sum += inputs[i];
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
