import java.util.*;
import java.io.*;

public class Main {
    static int[] input;
    static List<int[]> lst = new ArrayList<>();
    static int[] output;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        output = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        lst.add(new int[M]);

        input = Arrays.stream(input).distinct().sorted().toArray();
        N = input.length;
        dfs(0);

    }

    private static void dfs(int cnt) {
        if (cnt == M) {
            lst.add(output);
            for (int i = 0 ; i < M ; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        } else if (cnt == 0) {
            for (int i = 0 ; i < N ; i++) {
                output[0] = input[i];
                dfs(1);
            }
        }

        else {
            for (int i = 0 ; i < N ; i++) {
                if (output[cnt - 1] <= input[i]) {
                    if (lst.get(lst.size() - 1)[cnt] == input[i] && output[cnt - 1] != input[i]) continue;
                    else {
                        output[cnt] = input[i];
                        dfs(cnt + 1);
                    }
                }
            }
        }
    }
}