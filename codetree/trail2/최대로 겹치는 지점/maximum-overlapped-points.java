import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] arr = new int[N][2];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }


        int ans = 0;
        int[] overlap = new int[101];
        for (int i = 0 ; i < N ; i++) {
            for (int j = arr[i][0] ; j <= arr[i][1] ; j++) {
                overlap[j]++;
            }
        }

        for (int i = 0 ; i <= 100 ; i++) {
            ans = Math.max(ans, overlap[i]);
        }
        System.out.print(ans);

    }
}
