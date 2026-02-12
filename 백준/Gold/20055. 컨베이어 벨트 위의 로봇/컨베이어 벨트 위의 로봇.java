import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K;
    public static void main(String[] args) throws IOException {
        int[] belt = input();
        boolean[] robots = new boolean[belt.length];

        int step = 1;
        int up = 0;
        int down = belt.length / 2 - 1;
        int durability = 0;
        while (true) {
            // 1. 벨트 회전
            up = (up - 1 + belt.length) % belt.length;
            down = (down - 1 + belt.length) % belt.length;
            robots[down] = false;

            // 2. 로봇 이동
            int idx = down;
            for (int i = 0 ; i < belt.length / 2 ; i++) {
                idx = (idx - 1 + belt.length) % belt.length;
                if (robots[idx]) {
                    int nextIdx = (idx + 1) % belt.length;
                    if (!robots[nextIdx] && belt[nextIdx] > 0) {
                        robots[idx] = false;
                        if (nextIdx != down) {
                            robots[nextIdx] = true;
                        }
                        belt[nextIdx]--;
                        if (belt[nextIdx] == 0) {
                            durability++;
                        }
                    }
                }
            }

            // 3. 올리는 위치에 로봇 올리기
            if (belt[up] > 0) {
                robots[up] = true;
                belt[up]--;
                if (belt[up] == 0) {
                    durability++;
                }
            }

            // 종료 조건
            if (durability >= K) {
                break;
            }

            step++;

        }

        System.out.println(step);
    }

    private static int[] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] inputs = new int[N * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N * 2 ; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        return inputs;
    }
}
