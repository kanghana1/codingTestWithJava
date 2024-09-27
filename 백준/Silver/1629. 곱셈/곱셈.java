import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = myPow(A, B, C);
        System.out.println(ans);

    }

    private static long myPow(int a, int b, int c) {
        if (b == 1) return (a % c);

        long sol = myPow(a, b / 2, c);
        if (b % 2 == 1) {
            return sol * sol % c * a % c;
        }
        return sol * sol % c;
    }
}