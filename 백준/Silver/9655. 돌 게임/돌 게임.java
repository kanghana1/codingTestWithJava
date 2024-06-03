import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 3 * 짝수 : 창영
        // 짝수 : 창영
        // 3 * 홀수 : 상근
        // 홀수 : 상근
        if (n % 2 == 0) System.out.println("CY");
        else System.out.println("SK");
    }
}
