import java.io.*;
import java.util.*;
import static java.lang.Math.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] inputs = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1 ; i <= N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        int[] upDp = up(inputs);
        int[] downDp = down(inputs);
        int max = 0;
        for (int i = 1 ; i <= N ; i++) {
            max = Math.max(max, upDp[i] + downDp[i]);
        }
        System.out.println(max - 1);
    }

    private static int[] down(int[] inputs) {
        int[] downDp = new int[inputs.length];
        downDp[inputs.length - 1] = 1;
        for (int i = inputs.length - 2 ; i >= 1 ; i--) {
            for (int j = i + 1 ; j < inputs.length ; j++) {
                if (inputs[i] > inputs[j]) downDp[i] = max(downDp[j] + 1, downDp[i]);
            }
            downDp[i] = max(1, downDp[i]);
        }
        return downDp;
    }

    private static int[] up(int[] inputs) {
        int[] upDp = new int[inputs.length];
        for (int i = 1 ; i < inputs.length ; i++) {
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (inputs[i] > inputs[j]) {
                    upDp[i] = max(upDp[j] + 1, upDp[i]);
                }
            }
        }

        return upDp;
    }
}