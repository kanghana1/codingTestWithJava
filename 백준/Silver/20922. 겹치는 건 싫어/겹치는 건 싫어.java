import java.io.*;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N];
        int[] cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = 0;
        int max = -1;
        while (end < N) {
            while (end < N && cnt[inputs[end]] < limit) {
                cnt[inputs[end]]++;
                end++;
            }
            int length = end - start;
            max = Math.max(max, length);
            cnt[inputs[start]]--;
            start++;
        }
        System.out.println(max);
    }
}