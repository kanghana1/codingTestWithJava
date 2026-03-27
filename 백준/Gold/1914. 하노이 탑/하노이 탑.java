import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(getLeastMoveCount(N));
        if (N <= 20){
            hanoi(N, 1, 3);
            System.out.println(sb);
        }

    }

    static BigInteger getLeastMoveCount(int discs) {
        return new BigInteger("2").pow(discs).subtract(BigInteger.ONE);
    }

    private static void hanoi(int n , int start, int end) {
        if (n == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        hanoi(n - 1, start, 6 - start - end);
        sb.append(start).append(" ").append(end).append("\n");
        hanoi(n - 1, 6 - start - end, end);
    }
}
