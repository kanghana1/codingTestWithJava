import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < N) {
            while (end < N && sum < min) {
                sum += inputs[end];
                end++;
            }
            while (start < N && sum >= min) {
                ans = Math.min(ans, end - start);
                sum -= inputs[start];
                start++;
            }
        }
        if (ans == Integer.MAX_VALUE) ans = 0;
        System.out.println(ans);
    }
}