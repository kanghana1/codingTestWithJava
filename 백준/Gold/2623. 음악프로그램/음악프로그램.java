import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> lst = new ArrayList<>();
    static boolean[] isVisit;
    static boolean[] check; // 무한루프 방지
    static boolean canDo = true;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        isVisit = new boolean[N + 1];
        for (int i = 0 ; i <= N  ; i++) {
            lst.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int bound = Integer.parseInt(st.nextToken());
            int[] input = new int[bound];
            for (int j = 1 ; j <= bound ; j++) {
                input[j - 1] = Integer.parseInt(st.nextToken());
            }
            if (bound == 1) continue;
            for (int j = 1 ; j < bound ; j++) {
                for (int k = j - 1 ; k >= 0 ; k--) {
                    lst.get(input[j]).add(input[k]);
                }
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            if (lst.get(i).isEmpty() && !isVisit[i]) {
                sb.append(i).append("\n");
                isVisit[i] = true;
                continue;
            }
            check = new boolean[N + 1];
            check[i] = true;
            if (!isVisit[i]) dfs(i);
            check[i] = false;
        }
        if (!canDo) System.out.println(0);
        else System.out.println(sb.toString());
    }

    private static void dfs(int idx) {
        List<Integer> list = lst.get(idx);
        if (lst.get(idx).isEmpty()) {
            sb.append(idx).append("\n");
            isVisit[idx] = true;
            return;
        }
        for (int i = 0 ; i < list.size() ; i++) {
            if (check[list.get(i)]) {
                canDo = false;
                return;
            }
            else if (!isVisit[list.get(i)]) {
                check[list.get(i)] = true;
                dfs(list.get(i));
                check[list.get(i)] = false;
            }

        }
        sb.append(idx).append("\n");
        isVisit[idx] = true;
    }
}