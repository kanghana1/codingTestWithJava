import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {

    static int[] arr;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean isAllMinor = true;
        for (int i = 0 ; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] >= 0) isAllMinor = false;
        }

        int idx = 0;
        int max = 0;
        int sum = 0;
        while (idx < n) {

            if (isAllMinor) {
                max = Arrays.stream(arr).max().getAsInt();
                break;
            }
            if (sum + arr[idx] < 0) {
                max = max(sum, max);
                sum = 0;
                idx++;
            } else {
                sum += arr[idx];
                max = max(sum, max);
                idx++;
            }
        }
        System.out.println(max);



    }
}