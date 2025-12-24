import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int r;
    static int c;
    static int k;
    static int N = 100;

    public static void main(String[] args) throws IOException {
        input();
        int ans = operation();
        if (ans > 100) {
            ans = -1;
        }
        System.out.println(ans);
    }

    private static int operation() {
        int rCnt = 3;
        int cCnt = 3;
        int ans = 0;
        while (ans <= 100) {
            if (arr[r][c] == k) {
                break;
            }

            if (rCnt >= cCnt) {
                cCnt = rType(rCnt, cCnt);
            } else {
                rCnt = cType(rCnt, cCnt);
            }
            ans++;
        }

        return ans;
    }

    private static int cType(int rCnt, int cCnt) {
        int max = 0;
        for (int i = 1 ; i <= cCnt ; i++) {
            int cnt = cOper(i, rCnt);
            max = Math.max(max, cnt);
        }
        return max;
    }

    private static int rType(int rCnt, int cCnt) {
        int max = 0;
        for (int i = 1 ; i <= rCnt ; i++) {
            int cnt = rOper(i, cCnt);
            max = Math.max(max, cnt);
        }
        return max;
    }

    private static int rOper(int idx, int cCnt) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1 ; i <= cCnt ; i++) {
            if (arr[idx][i] == 0) continue;
            queue.add(arr[idx][i]);
        }

        if (queue.isEmpty()) {
            // 이 행은 전부 0이어야 함 (뒤의 0 정리도 같이)
            for (int j = 1; j <= 100; j++) arr[idx][j] = 0; // rOper 기준
            return 0;
        }

        int num = queue.poll();
        int cnt = 1;
        PriorityQueue<Num> pq = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (num == n) cnt++;
            else {
                pq.add(new Num(num, cnt));
                num = n;
                cnt = 1;
            }
        }
        pq.add(new Num(num, cnt));

        int cIdx = 1;
        while (!pq.isEmpty()) {
            if (cIdx > 100) break;
            Num poll = pq.poll();
            arr[idx][cIdx] = poll.num;
            arr[idx][++cIdx] = poll.cnt;
            cIdx++;
        }

        for (int i = cIdx ; i <= 100 ; i++) {
            arr[idx][i] = 0;
        }

        return cIdx - 1;
    }

    private static int cOper(int idx, int rCnt) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 1 ; i <= rCnt ; i++) {
            if (arr[i][idx] == 0) continue;
            queue.add(arr[i][idx]);
        }

        if (queue.isEmpty()) {
            for (int j = 1; j <= N; j++) arr[idx][j] = 0;
            return 0;
        }

        int num = queue.poll();
        int cnt = 1;
        PriorityQueue<Num> pq = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (num == n) cnt++;
            else {
                pq.add(new Num(num, cnt));
                num = n;
                cnt = 1;
            }
        }
        pq.add(new Num(num, cnt));

        int rIdx = 1;
        while (!pq.isEmpty()) {
            if (rIdx > 100) break;
            Num poll = pq.poll();
            arr[rIdx][idx] = poll.num;
            arr[++rIdx][idx] = poll.cnt;
            rIdx++;
        }

        for (int i = rIdx ; i <= 100 ; i++) {
            arr[i][idx] = 0;
        }

        return rIdx - 1;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[101][101];
        for (int i = 1 ; i <= 3 ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= 3 ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Num implements Comparable<Num> {
        int num;
        int cnt;

        public Num(int n, int c) {
            this.num = n;
            this.cnt = c;
        }

        @Override
        public int compareTo(Num o) {
            if (cnt == o.cnt) return num - o.num;
            return cnt - o.cnt;
        }
    }
}

