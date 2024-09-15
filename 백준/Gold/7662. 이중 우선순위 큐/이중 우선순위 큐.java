import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> countMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < T ; i++) {
            int N = Integer.parseInt(br.readLine());
            countMap = new HashMap<>();
            Queue<Integer> min = new PriorityQueue<>();
            Queue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

            for (int j = 0 ; j < N ; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String order = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (order.equals("I")) {
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1); // 없으면 디폴트 0으로 설정, 있으면 get. 이 값의 + 1
                    min.add(num);
                    max.add(num);
                } else {
                    if (countMap.size() == 0) continue; // 무시
                    if (num == 1) {
                        // 큰값 제거
                        rmMap(max);
                    } else {
                        // 작은 값 제거
                        rmMap(min);
                    }
                }
            }
            if (countMap.size() == 0) sb.append("EMPTY").append("\n");
            else {
                int MAX = rmMap(max);
                sb.append(MAX + " " + (countMap.size() > 0 ? rmMap(min) : MAX)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static int rmMap(Queue<Integer> queue) {
        int val = 0;
        while (true) {
            val = queue.poll();
            int cnt = countMap.getOrDefault(val, 0);
            if (cnt == 0) continue;

            if (cnt == 1) countMap.remove(val);
            else countMap.put(val, cnt - 1);
            break;
        }

        return val;
    }
}