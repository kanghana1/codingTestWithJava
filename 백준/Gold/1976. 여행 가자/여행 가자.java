import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 1976
public class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static int[] trips;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시 수
        int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 수
        StringTokenizer st;

        int[][] map = new int[N + 1][N + 1];
        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        trips = new int[M];
        for (int i = 0 ; i < M ; i++) {
            trips[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 기반으로 양방향 그래프 만들기
        makeGraph(map);

        // 탐색
        boolean ans = search();
        System.out.println(ans ? "YES" : "NO");

    }
    /*
    * 시작 노드 기준 전체탐색 (연결이 되어있다면 다른 곳을 경유해서라도 갈 수는 있음)
    * -> 가야하는 여행경로 중 시작노드와 연결되지 않은 노드가 하나라도 있으면 false
    * */
    private static boolean search() {
        int start = trips[0];
        boolean[] isVisit = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer city = queue.poll();
            isVisit[city] = true;
            for (int next : graph.get(city)) {
                if (!isVisit[next]) {
                    queue.add(next);
                }
            }
        }

        for (int trip : trips) {
            if (!isVisit[trip]) return false;
        }
        return true;
    }

    private static void makeGraph(int[][] map) {
        // 리스트 속 리스트 생성
        for (int i = 0 ; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 생성
        for (int i = 1 ; i <= N ; i++) {
            for (int j = i ; j <= N ; j++) {
                if (map[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i); // 양방향
                }
            }
        }
    }
}
