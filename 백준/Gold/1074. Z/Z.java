import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static Long cnt = 0L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);

        rec(r, c, size);
        System.out.println(cnt);

    }

    private static void rec(int r, int c, int size) {
        if (size == 1) return;

        // 2사분면
        if (r < size / 2 && c < size / 2) {
            rec(r, c, size / 2);
        } else if (r < size / 2 && c >= size / 2) { // 1사분면
            cnt += size * size / 4;
            rec(r, c - (size / 2), size / 2);
        } else if (r >= size / 2 && c < size / 2) { // 3사분면
            cnt += (size * size / 4) * 2;
            rec(r - (size / 2), c, size / 2);
        } else {
            cnt += (size * size / 4) * 3;
            rec(r - size / 2, c - size / 2, size / 2);
        }
    }
}