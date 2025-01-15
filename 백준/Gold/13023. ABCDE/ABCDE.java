import java.io.*;
import java.util.*;
public class Main {
    static List<List<Integer>> friends = new ArrayList<>();
    static boolean[] isVisit;
    static boolean ans = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        isVisit = new boolean[N];
        for (int i = 0 ; i < N ; i++) {
            friends.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int per1 = Integer.parseInt(st.nextToken());
            int per2 = Integer.parseInt(st.nextToken());
            friends.get(per1).add(per2);
            friends.get(per2).add(per1);
        }

        for (int i = 0 ; i < N ; i++) {
            if (ans) break;
            isVisit[i] = true;
            relation(i, 1);
            isVisit[i] = false;
        }
        System.out.println(ans ? 1 : 0);

    }

    private static void relation(int start, int num) {
        if (num == 5) {
            ans = true;
            return;
        }
        List<Integer> friend = friends.get(start);
        for (int i = 0 ; i < friend.size() ; i++) {
            if (!isVisit[friend.get(i)]) {
                isVisit[friend.get(i)] = true;
                relation(friend.get(i), num + 1);
                isVisit[friend.get(i)] = false;
            }
        }
    }
}