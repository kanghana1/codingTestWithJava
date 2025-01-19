import java.io.*;
import java.util.*;

public class Main {
    static int[][] inputs;
    static boolean[][] isVisit;
    static int[] dx = new int[]{0, 1, 0, -1}; // 북, 동, 남, 서
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int r;
    static int c;
    static int cnt = 0;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        inputs = new int[r][c];
        isVisit = new boolean[r][c];

        for (int i = 0 ; i < r ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < c ; j++) {
                int num = Integer.parseInt(st.nextToken());
                inputs[i][j] = num;
            }
        }

        for (int i = 0 ; i < r ; i++) {
            for (int j = 0 ; j < c ; j++) {
                if (canGo(i, j)) {
                    cnt++;
                    int width = find2(i, j);
                    max = Math.max(max, width);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n").append(max);
        System.out.println(sb);
    }
    private static boolean canGo(int i, int j) {
        return i >= 0 && i < r && j >= 0 && j < c && !isVisit[i][j] && (inputs[i][j] == 1);
    }

    private static int find2(int i, int j) {
        int width = 1;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(i, j));
        isVisit[i][j] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int t = 0 ; t < 4 ; t++) {
                if (canGo(node.y + dy[t], node.x + dx[t])) {
                    queue.add(new Node(node.y + dy[t], node.x + dx[t]));
                    isVisit[node.y + dy[t]][node.x + dx[t]] = true;
                    width++;
                }
            }
        }

        return width;
    }
}
class Node {
    int y;
    int x;
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}