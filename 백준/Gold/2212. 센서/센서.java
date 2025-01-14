import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inputs = new int[N];
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(inputs);

        int[] gap = new int[N - 1];
        long sum = 0;
        for (int i = 1 ; i < N ; i++) {
            gap[i - 1] = inputs[i] - inputs[i - 1];
            sum += gap[i - 1];
        }

        Arrays.sort(gap);
        if (N >= 2) {
            for (int i = 0 ; i < K - 1 ; i++) {
                sum -= gap[N - 2 - i];
            }
        }
        System.out.println(sum);
    }
}