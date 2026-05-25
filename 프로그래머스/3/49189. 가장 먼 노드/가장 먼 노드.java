import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> lst = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            lst.add(new ArrayList<>());
        }
        
        for (int i = 0 ; i < edge.length ; i++) {
            lst.get(edge[i][0]).add(edge[i][1]);
            lst.get(edge[i][1]).add(edge[i][0]);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : lst.get(now)) {
                if (dis[next] == Integer.MAX_VALUE) {
                    dis[next] = dis[now] + 1;
                    queue.add(next);
                }
            }
        }
        
        int answer = 0;
        int val = 0;
        for (int i = 2 ; i <= n ; i++) {
            if (dis[i] > val) {
                answer = 1;
                val = dis[i];
            } else if (dis[i] == val) answer++;
        }
        return answer;
    }
}