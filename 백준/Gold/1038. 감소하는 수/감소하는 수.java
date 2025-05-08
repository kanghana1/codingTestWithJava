import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Long> dec = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0 ; i <= 9 ; i++) {
            decNum((long) i);
        }
        if (N >= dec.size()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(dec);
        System.out.println(dec.get(N));
    }

    private static void decNum(long n) {
        dec.add(n);
        long now = n % 10;
        for (int i = 0 ; i < now ; i++) {
            decNum(n * 10 + i);
        }
    }
}
