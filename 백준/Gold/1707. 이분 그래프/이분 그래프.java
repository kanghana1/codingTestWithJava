import java.io.*;
import java.util.*;
public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static int[] colors;
    static final int RED = 1;
    static final int BLUE = -1;
    static boolean isBin = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());
        for (int t = 0 ; t < test ; t++) {
            isBin = true;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            colors = new int[node + 1];
            for (int i = 0 ; i <= node ; i++) {
                lst.add(new ArrayList<>());
            }

            for (int i = 0 ; i < edge ; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                lst.get(start).add(end);
                lst.get(end).add(start);
            }

            for (int i = 1 ; i <= node ; i++) {
                if (!isBin) break;
                if (colors[i] == 0) dfs(i, RED);
            }

            sb.append(isBin ? "YES" : "NO").append("\n");
            lst.clear();
        }
        System.out.println(sb);
    }

    private static void dfs(int start, int color) {
        colors[start] = color;
        List<Integer> child = lst.get(start);
        if (!isBin) {
            return;
        }
        for (int ch : child) {
            if (color == colors[ch]) {
                isBin = false;
                return;
            }
            if (colors[ch] == 0) {
                dfs(ch, -1 * color);
            }
        }
    }
}
