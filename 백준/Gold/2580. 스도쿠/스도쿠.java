import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node> blanks = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int[][] inputs = new int[9][9];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0 ; i < 9 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < 9 ; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 0) blanks.add(new Node(i, j));
                inputs[i][j] = a;
            }
        }
        dfs(0, inputs);
        System.out.print(sb);
    }

    private static boolean dfs(int idx, int[][] arr) {
        if (idx == blanks.size()) {
            print(arr);
            return true;
        }
        int y = blanks.get(idx).y;
        int x = blanks.get(idx).x;
        for (int i = 1 ; i <= 9 ; i++) {
            if (canFill(y, x, i, arr)) {
                arr[y][x] = i;
                boolean ans = dfs(idx + 1, arr);
                if (ans) return true;
                arr[y][x] = 0;
            }
        }
        return false;
    }

    private static void print(int[][] arr) {
        for (int i = 0 ; i < 9 ; i++) {
            for (int j = 0 ; j < 9 ; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
    }

    private static boolean canFill(int y, int x, int val, int[][] arr) {
        if (width(y, x, val, arr) && hei(y, x, val, arr) && ract(y, x, val, arr)) return true;
        return false;
    }

    private static boolean ract(int y, int x, int val, int[][] arr) {
        // 0 1 2 | 3 4 5 | 6 7 8
        int yVal = y / 3;
        int xVal = x / 3;
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                if ((yVal * 3 + i) == y && (xVal * 3 + j) == x) continue;
                if (arr[yVal * 3 + i][xVal * 3 + j] == val) return false;
            }
        }
        return true;
    }

    private static boolean hei(int y, int x, int val, int[][] arr) {
        for (int i = 0 ; i < 9 ; i++) {
            if (y == i) continue;
            if (arr[i][x] == val) return false;
        }
        return true;
    }

    private static boolean width(int y, int x, int val, int[][] arr) {
        for (int i = 0 ; i < 9 ; i++) {
            if (x == i) continue;
            if (arr[y][i] == val) return false;
        }
        return true;
    }
}
class Node {
    int y;
    int x;
    int value;
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}