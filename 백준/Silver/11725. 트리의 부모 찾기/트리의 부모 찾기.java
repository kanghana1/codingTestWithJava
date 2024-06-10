import java.util.*;
import java.io.*;


public class Main {

    static int n;
    static boolean[] isVisit;
    static List<Integer>[] mainArr;
    static int[] solArr;

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        mainArr = new List[n];
        solArr = new int[n];
        isVisit = new boolean[n];
        for (int i = 0 ; i < n ; i++) {
            mainArr[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < n - 1 ; i++) { // 자식에다가 부모 넣어두기
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            mainArr[child - 1].add(parent);
            mainArr[parent - 1].add(child);
        }
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i < n ; i++) {
            sb.append(solArr[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int value) {
        int idx = value - 1;
        isVisit[idx] = true;
        for (int i = 0 ; i < mainArr[idx].size() ; i++) {
            if (!isVisit[mainArr[idx].get(i) - 1]) {
                solArr[mainArr[idx].get(i) - 1] = value;
                dfs(mainArr[idx].get(i));
            }
        }
    }
}
