import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] world;
    static int[][] groups;

    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] dx = new int[]{1, 0, -1, 0};

    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());
        world = new int[N][N];
        groups = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                world[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
//            print();
            boolean isOpen = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (groups[i][j] == 0) {
                        boolean enter = false;
                        for (int k = 0 ; k < 4 ; k++) {
                            if (canGo(i + dy[k], j + dx[k]) && Math.abs(world[i][j] - world[i + dy[k]][j + dx[k]]) >= min && Math.abs(world[i][j] - world[i + dy[k]][j + dx[k]]) <= max) {
                                enter = true;
                                break;
                            }
                        }
                        if (!enter) continue;
                        isOpen = true;
                        int avg = bfs(i, j, N * i + j + 1);
                        world[i][j] = avg;
                    } else {
                        int value = groups[i][j] - 1;
                        int y = value / N;
                        int x = value % N;
                        world[i][j] = world[y][x];
                    }
                }
            }
            if (!isOpen) break;
            cnt++;
            groups = new int[N][N];
        }
        System.out.println(cnt);
    }

    private static void print() {
        System.out.printf("===========================\n");
        for (int i = 0 ; i < groups.length ; i++) {
            for (int j = 0 ; j < groups.length ; j++) {
                System.out.print(world[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int bfs(int y, int x, int groupId) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(y, x));
        groups[y][x] = groupId;
        int sum = world[y][x];
        int cnt = 1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (canGo(ny, nx)) {
                    int diff = Math.abs(world[node.y][node.x] - world[ny][nx]);
                    if (diff >= min && diff <= max) {
                        queue.add(new Node(ny, nx));
                        groups[ny][nx] = groupId;
                        sum += world[ny][nx];
                        cnt++;
                    }

                }
            }
        }

        return sum / cnt;
    }

    private static boolean canGo(int y, int x) {
        return y >= 0 && y < world.length && x >= 0 && x < world.length && groups[y][x] == 0;
    }
}

class Node{
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
