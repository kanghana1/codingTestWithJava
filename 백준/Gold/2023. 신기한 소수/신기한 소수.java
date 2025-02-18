import java.io.*;
public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        specialPrime(0, 0, N);
        System.out.println(sb);
    }

    private static void specialPrime(int nowVal, int nowSize, int end) {
        if (nowSize == end) {
            if (isPrime(nowVal)) sb.append(nowVal).append("\n");
            return;
        }
        for (int i = 1 ; i <= 9 ; i++) {
            if (isPrime(nowVal * 10 + i)) {
                specialPrime(nowVal * 10 + i, nowSize + 1, end);
            }
        }
    }

    private static boolean isPrime(int now) {
        if (now == 1) return false;
        for (int i = 2 ; i <= Math.sqrt(now) ; i++) {
            if (now != i && now % i == 0) {
                return false;
            }
        }
        return true;
    }
}