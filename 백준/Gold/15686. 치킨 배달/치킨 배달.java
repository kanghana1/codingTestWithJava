import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {
    static final int BLANK = 0;
    static final int HOUSE = 1;
    static final int CHICKEN = 2;
    static List<Node> store = new ArrayList<>();
    static List<Node> house = new ArrayList<>();
    static boolean[] isVisit;
    static int M;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] inputs = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                int num = Integer.parseInt(st.nextToken());
                inputs[i][j] = num;
                if (num == CHICKEN) store.add(new Node(i, j));
                else if (num == HOUSE) house.add(new Node( i, j));
            }
        }
        isVisit = new boolean[store.size()];
        search(0, 0);
        System.out.println(ans);
    }

    private static void search(int idx, int cnt) {
        if (cnt == M) {
            int sum = 0;
            for (int i = 0 ; i < house.size() ; i++) {
                int near = Integer.MAX_VALUE;
                for (int j = 0 ; j < store.size() ; j++) {
                    if (isVisit[j]) {
                        int dist = abs(house.get(i).x - store.get(j).x) + abs(house.get(i).y - store.get(j).y);
                        near = min(near, dist);
                    }
                }
                sum += near;
            }
            ans = min(ans, sum);
            return;
        }
        for (int i = idx ; i < store.size() ; i++) { // 완전탐색
            if (!isVisit[i]) {
                isVisit[i] = true;
                search(i + 1, cnt + 1);
                isVisit[i] = false;
            }
        }
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