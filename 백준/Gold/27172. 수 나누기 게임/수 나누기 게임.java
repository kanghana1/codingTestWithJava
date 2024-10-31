import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inputs = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < N ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, inputs[i]);
        }
        int[] score = new int[N + 1];
        int[] index = new int[max + 1];

        for (int i = 0 ; i < N ; i++) {
            index[inputs[i]] = i + 1;
        }

        for (int obj : inputs) {
            for (int i = 2 * obj ; i <= max ; i += obj) {
                if (index[i] != 0) {
                    score[index[i]]--;
                    score[index[obj]]++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= N ; i++) {
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}