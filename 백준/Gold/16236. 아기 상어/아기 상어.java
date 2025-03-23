import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] inputs;
    static int sharkSize = 2;
    static int[] dy = new int[]{-1, 0, 1, 0}; // 위 왼 아래 오
    static int[] dx = new int[]{0, -1, 0, 1};
    static int sharkY;
    static int sharkX;
    static int eatCnt = 0;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputs = new int[N][N];


        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                inputs[i][j] = Integer.parseInt(st.nextToken());
                if (inputs[i][j] == 9) {
                    sharkY = i;
                    sharkX = j;
                    inputs[i][j] = 0;
                }
            }
        }

        while(true) {
            Node fish = findfish(sharkY, sharkX);
            if (fish == null) break;
            ans += fish.cnt;
            sharkY = fish.y;
            sharkX = fish.x;
            inputs[sharkY][sharkX] = 0;
            eatCnt++;

            if (eatCnt == sharkSize) {
                eatCnt = 0;
                sharkSize++;
            }
        }
        System.out.println(ans);
    }

    private static Node findfish(int y, int x) {
        PriorityQueue<Node> canEat = new PriorityQueue<>();
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] isVisit = new boolean[inputs.length][inputs.length];
        queue.add(new Node(y, x, 0));
        isVisit[y][x] = true;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if (canGo(nextY, nextX) && !isVisit[nextY][nextX]) {
                    isVisit[nextY][nextX] = true;
                    Node next = new Node(nextY, nextX, now.cnt + 1);
                    if (inputs[nextY][nextX] < sharkSize && inputs[nextY][nextX] >= 1) {
                        // 먹을 수 있는 경우
                        canEat.add(next);
                    } else {
                        queue.add(next);
                    }

                }
            }
        }
        return canEat.isEmpty() ? null : canEat.poll();
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < inputs.length && x >= 0 && x < inputs.length && inputs[y][x] <= sharkSize;
    }
}

class Node implements Comparable<Node> {
    public int y;
    public int x;
    public int cnt;
    public Node(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        if (this.cnt != o.cnt) return Integer.compare(this.cnt, o.cnt);
        if (this.y != o.y) return Integer.compare(this.y, o.y);
        return Integer.compare(this.x, o.x);
    }
}
