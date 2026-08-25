import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int x = routes.length; 

        List<List<int[]>> robotPaths = new ArrayList<>();
        int maxTime = 0;

        for (int i = 0; i < x; i++) {
            List<int[]> path = new ArrayList<>();
            int[] startPoint = points[routes[i][0] - 1];
            int curR = startPoint[0];
            int curC = startPoint[1];
            path.add(new int[]{curR, curC});

            for (int j = 1; j < routes[i].length; j++) {
                int[] nextPoint = points[routes[i][j] - 1];
                int targetR = nextPoint[0];
                int targetC = nextPoint[1];

                
                while (curR != targetR) {
                    if (curR < targetR) curR++;
                    else curR--;
                    path.add(new int[]{curR, curC});
                }
               
                while (curC != targetC) {
                    if (curC < targetC) curC++;
                    else curC--;
                    path.add(new int[]{curR, curC});
                }
            }
            
            robotPaths.add(path);
            maxTime = Math.max(maxTime, path.size());
        }

        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> map = new HashMap<>();
            
            for (int i = 0; i < x; i++) {
                if (t < robotPaths.get(i).size()) {
                    int[] pos = robotPaths.get(i).get(t);
                    String key = pos[0] + "," + pos[1];
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }
            
        
            for (int count : map.values()) {
                if (count >= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}