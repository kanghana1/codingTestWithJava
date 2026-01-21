import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 18428
/*
* YES :
* */
public class Main {
    static char[][] arr;
    static int[][] defense; // student : -1, teacher : -2
    static List<int[]> students = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        fillArray();
        boolean possible = earlyCheck();
        if (!possible) {
            System.out.println("NO");
            return;
        }
        examineStudent();
        boolean ans = isPossible();
        System.out.println(ans ? "YES" : "NO");
    }

    private static boolean isPossible() {
        List<int[]> candidates = new ArrayList<>();
        for (int i = 0 ; i < defense.length ; i++) {
            for (int j = 0 ; j < defense.length ; j++) {
                if (defense[i][j] > 0 && arr[i][j] == 'X') {
                    candidates.add(new int[]{i, j});
                }
            }
        }

        if (candidates.isEmpty()) return true;

        if (candidates.size() < 3) {
            for (int[] pos : candidates) {
                arr[pos[0]][pos[1]] = 'O';
            }
            boolean safe = !isAnyStudentSeen();
            for (int[] pos : candidates) {
                arr[pos[0]][pos[1]] = 'X';
            }
            return safe;
        }

        int size = candidates.size();
        for (int i = 0 ; i < size - 2 ; i++) {
            int[] a = candidates.get(i);
            arr[a[0]][a[1]] = 'O';
            for (int j = i + 1 ; j < size - 1 ; j++) {
                int[] b = candidates.get(j);
                arr[b[0]][b[1]] = 'O';
                for (int k = j + 1 ; k < size ; k++) {
                    int[] c = candidates.get(k);
                    arr[c[0]][c[1]] = 'O';
                    if (!isAnyStudentSeen()) return true;
                    arr[c[0]][c[1]] = 'X';
                }
                arr[b[0]][b[1]] = 'X';
            }
            arr[a[0]][a[1]] = 'X';
        }
        return false;
    }

    private static void examineStudent() {
        for (int k = 0 ; k < students.size() ; k++) {
            int[] student = students.get(k);
            check(student[0], student[1]);
        }
    }

    /*
     * 동/서/남/북 방향에 바로 선생님이 있는지 체크해 감시 피할 수 있는지 확인
     * YES : true / NO : false
    * */
    private static boolean earlyCheck() {
        int[] dy = new int[]{0, 1, 0, -1};
        int[] dx = new int[]{1, 0, -1, 0};
        for (int k = 0 ; k < students.size() ; k++) {
            int[] student = students.get(k);
            int y = student[0];
            int x = student[1];
            for (int i = 0 ; i < 4 ; i++) {
                if (canCheck(y + dy[i], x + dx[i])) {
                    if (arr[y + dy[i]][x + dx[i]] == 'T') return false;
                }

            }
        }
        return true;
    }

    private static boolean canCheck(int y, int x) {
        return y >= 0 && y < arr.length && x >= 0 && x < arr.length;
    }

    private static void check(int y, int x) {
        checkUp(y, x);
        checkRight(y, x);
        checkLeft(y, x);
        checkDown(y, x);
    }

    private static void checkDown(int y, int x) {
        // x는 고정, y는 큰 것들 탐색
        for (int i = y + 1 ; i < arr.length ; i++) {
            if (arr[i][x] == 'T') {
                for (int j = y + 1 ; j < i ; j++) {
                    if (defense[j][x] >= 0) defense[j][x]++;
                }
                return;
            }
        }
    }

    private static void checkLeft(int y, int x) {
        // y는 고정, x는 작은 것들 탐색
        for (int i = x - 1 ; i >= 0 ; i--) {
            if (arr[y][i] == 'T') {
                for (int j = x - 1 ; j > i ; j--) {
                    if (defense[y][j] >= 0) defense[y][j]++;
                }
                return;
            }
        }
    }

    private static void checkRight(int y, int x) {
        // y는 고정, x는 큰 것들 탐색
        for (int i = x + 1 ; i < arr.length ; i++) {
            if (arr[y][i] == 'T') {
                for (int j = x + 1 ; j < i ; j++) {
                    if (defense[y][j] >= 0) defense[y][j]++;
                }
                return;
            }
        }
    }

    private static void checkUp(int y, int x) {
        // x는 고정, y는 작은것들 탐색
        for (int i = y - 1 ; i >= 0 ; i--) {
            if (arr[i][x] == 'T') {
                for (int j = y - 1 ; j > i ; j--) {
                    if (defense[j][x] >= 0) defense[j][x]++;
                }
                return;
            }
        }
    }

    private static boolean isAnyStudentSeen() {
        int[] dy = new int[]{-1, 1, 0, 0};
        int[] dx = new int[]{0, 0, -1, 1};
        for (int[] student : students) {
            int y = student[0];
            int x = student[1];
            for (int dir = 0 ; dir < 4 ; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                while (ny >= 0 && ny < arr.length && nx >= 0 && nx < arr.length) {
                    if (arr[ny][nx] == 'O') break;
                    if (arr[ny][nx] == 'T') return true;
                    ny += dy[dir];
                    nx += dx[dir];
                }
            }
        }
        return false;
    }

    private static void fillArray() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        defense = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                arr[i][j] = st.nextToken().charAt(0);
                if (arr[i][j] == 'S') {
                    defense[i][j] = -1;
                    students.add(new int[]{i, j});
                }
                else if (arr[i][j] == 'T') defense[i][j] = -2;
            }
        }
    }
}
