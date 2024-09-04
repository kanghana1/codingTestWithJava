import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] inputs = new int[N + 1];
        int[] sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        // 누적합 저장
        sum[1] = inputs[1];
        for (int i = 2 ; i <= N ; i++) {
            sum[i] = inputs[i] + sum[i - 1];
        }

        int[] ans = new int[N - X];
        int max = -1;
        int cnt = 0;
        for (int i = X ; i <= N ; i++) {
            int sol = sum[i] - sum[i - X];
            if (max == sol) cnt++;
            else if (max < sol) {
                max = sol;
                cnt = 1;
            }
        }
        if (max == 0) bw.write("SAD");
        else bw.write(max + "\n" + cnt);
        bw.flush();
        bw.close();
    }
}