import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static Map<Integer, Character> directionChanges = new HashMap<>();
    static int[] dy = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dx = {1, 0, -1, 0}; // 우, 하, 좌, 상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1]; // -1은 apple, 0은 빈칸, 양수는 뱀의 몸통

        int appleCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < appleCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = -1;
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            directionChanges.put(time, direction);
        }

        int time = play();
        System.out.println(time);
    }

    private static int play() {
        int time = 0;
        int headY = 1;
        int headX = 1;
        int tailY = 1;
        int tailX = 1;
        int nowDirection = 0; // 0: 우, 1: 하, 2: 좌, 3: 상

        while (true) {
            time++;

            headY += dy[nowDirection];
            headX += dx[nowDirection];

            if (isOutOfBounds(headY, headX) || map[headY][headX] >= 1) {
                return time; // 게임 종료
            } else if (map[headY][headX] == -1) {
                map[headY][headX] = time + 1; // 뱀의 몸통으로 표시
            } else {
                map[headY][headX] = time + 1; // 뱀의 몸통으로 표시
                int[] tailDir = moveTail(tailY, tailX);
                tailY = tailDir[0];
                tailX = tailDir[1];
            }

            nowDirection = changedDirection(time, nowDirection);
        }
    }

    private static int changedDirection(int time, int nowDirection) {
        if (directionChanges.containsKey(time)) {
            char direction = directionChanges.get(time);
            if (direction == 'L') {
                nowDirection = (nowDirection + 3) % 4; // 왼쪽으로 회전
            } else if (direction == 'D') {
                nowDirection = (nowDirection + 1) % 4; // 오른쪽으로 회전
            }
        }
        return nowDirection;
    }

    private static int[] moveTail(int tailY, int tailX) {
        map[tailY][tailX] = 0; // 꼬리 위치 초기화
        int newTailY = tailY;
        int newTailX = tailX;
        for (int i = 0; i < 4; i++) {
            int newY = tailY + dy[i];
            int newX = tailX + dx[i];
            if (!isOutOfBounds(newY, newX) && map[newY][newX] > 0) {
                if (map[newY][newX] < map[newTailY][newTailX] || map[newTailY][newTailX] == 0) {
                    newTailY = newY;
                    newTailX = newX;
                }
            }
        }
        return new int[]{newTailY, newTailX};
    }

    private static boolean isOutOfBounds(int y, int x) {
        return y < 1 || y > N || x < 1 || x > N;
    }
}