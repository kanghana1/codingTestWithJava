import java.io.*;
import java.util.*;
public class Main {
    static List<Integer> lst = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] dec = new boolean[N + 1];
        Arrays.fill(dec, true);
        dec[1] = false;
        // 소수구하기
        for (int i = 2 ; i <= N / 2 ; i++) {
            if (dec[i]) {
                for (int j = 2 ; j <= N / 2 ; j++) {
                    if (i * j > N) break;
                    dec[i * j] = false;
                }
            }
        }
        for (int i = 2 ; i <= N ; i++) {
            if (dec[i]) {
                lst.add(i);
            }
        }

        int first = 0;
        int last = 0;
        int cnt = 0;
        while (last < lst.size() && first < lst.size()) {
            if (sum(first, last) == N) {
                cnt++;
                first++;
            } else if (sum(first, last) < N) {
                last++;
            } else {
                first++;
            }
        }
        System.out.println(cnt);
    }

    private static int sum(int first, int last) {
        int sum = 0;
        for (int i = first ; i <= last ; i++) {
            sum += lst.get(i);
        }
        return sum;
    }
}