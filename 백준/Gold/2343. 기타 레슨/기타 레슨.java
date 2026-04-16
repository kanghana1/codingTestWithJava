import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int left = 0;   
        int right = 0;  

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]); // 최소는 가장 긴 강의 길이
            right += arr[i];               // 최대는 전체 합
        }

        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (countBluray(mid) <= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static int countBluray(int size) {
        int count = 1;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] > size) {
                count++;
                sum = arr[i];
            } else {
                sum += arr[i];
            }
        }

        return count;
    }
}