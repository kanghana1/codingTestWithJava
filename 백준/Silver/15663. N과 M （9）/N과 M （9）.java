import java.util.*;
import java.io.*;

public class Main {
    static int[] input;
    static boolean[] isVisit; // 방문 인덱스 저장
    static int[] output;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        input = new int[N];
        isVisit = new boolean[N];
        output = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0 ; i < N ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        dfs(0);
    }

    private static void dfs(int length) {
        if (length >= M) {
            for (int i = 0 ; i < M ; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        } else {
            int before = 0;
            for (int i = 0 ; i < N ; i++) {
                if (!isVisit[i] && input[i] != before) {
                    isVisit[i] = true;
                    output[length] = input[i];
                    before = input[i];
                    dfs(length + 1);
                    isVisit[i] = false;
                }
            }
        }

    }
}