import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        for (int i = 0 ; i <= N ; i++) {
            arr[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        int before = 1;
        int now = 1;
        while (now <= N && now >= before) {
            int value = (now - before) * (now + before);
            if (value < N) {
                now++;
            } else if (value > N) {
                before++;
            } else {
                sb.append(arr[now]).append("\n");
                now++;
            }
        }
        if (sb.isEmpty()) sb.append(-1);
        System.out.println(sb);
    }
}