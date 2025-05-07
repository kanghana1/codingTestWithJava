import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] count = new int[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        int left = 0;
        int ans = 0;

        for (int right = 0 ; right < N ; right++) {
            if (count[arr[right]] == 0) cnt++;
            count[arr[right]]++;

            while (cnt > 2) {
                count[arr[left]]--;
                if (count[arr[left]] == 0) cnt--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        System.out.println(ans);
    }
}