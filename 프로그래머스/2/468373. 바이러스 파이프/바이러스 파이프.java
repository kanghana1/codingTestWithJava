import java.util.*;
class Solution {
    static Map<Integer, List<int[]>> m = new HashMap<>();
    static int ans = 0;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        for (int i = 1 ; i <= n ; i++) {
            m.put(i, new ArrayList<>());
        }
        
        for (int i = 0 ; i < edges.length ; i++) {
            m.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
            m.get(edges[i][1]).add(new int[]{edges[i][0], edges[i][2]});
        }
        
        Set<Integer> start = new HashSet<>();
        start.add(infection);
        dfs(start, k);
        
        return ans;
    }
    
    public static void dfs(Set<Integer> inf, int k) {
        if (k == 0) {
            ans = Math.max(ans, inf.size());
            return;
        }
        
        for (int type = 1; type <= 3; type++) {
            Set<Integer> nextInfected = new HashSet<>(inf);
            Queue<Integer> queue = new LinkedList<>(inf);
        
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (!m.containsKey(curr)) continue;
            
                for (int[] next : m.get(curr)) {
                    int nextNode = next[0];
                    int pipeType = next[1];
                
                    if (pipeType == type && !nextInfected.contains(nextNode)) {
                        nextInfected.add(nextNode);
                        queue.add(nextNode); 
                    }
                }
            }
            dfs(nextInfected, k - 1);
        }
        
        
    }
}