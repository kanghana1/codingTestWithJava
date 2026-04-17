import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 거리가 가장 작은 값이 최대가 되도록?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] house = new int[N];
        fillArray(house, br);

        int right = (house[house.length - 1] - house[0]) / (M - 1);
        int left = 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            int count = 1;
            int last = house[0];
            for (int i = 1 ; i < house.length ; i++) {
                if (house[i] - last >= mid) {
                    count++;
                    last = house[i];
                }
            }
            if (count < M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        System.out.println(right);
    }

    private static void fillArray(int[] house, BufferedReader br) throws IOException {
        for (int i = 0 ; i < house.length ; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
    }
}