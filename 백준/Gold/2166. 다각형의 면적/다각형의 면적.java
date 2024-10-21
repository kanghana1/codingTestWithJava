import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] inputs = new int[2][N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputs[0][i] = Integer.parseInt(st.nextToken());
            inputs[1][i] = Integer.parseInt(st.nextToken());
        }
        long solution = 0L;
        for (int i = 0 ; i < N ; i++) {
            solution += (long) inputs[0][i] * inputs[1][(i + 1) % N];
            solution -= (long) inputs[1][i] * inputs[0][(i + 1) % N];
        }

        String sol = String.format("%.1f", Math.abs(solution) / 2.0);
        bw.write(sol);
        bw.flush();
        bw.close();
    }
}