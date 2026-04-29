import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> maps = new HashMap<>();
        for (int i = 0 ; i < clothes.length ; i++) {
            maps.put(clothes[i][1], maps.getOrDefault(clothes[i][1], 1) + 1);
        }
        
        for (int v : maps.values()) {
            answer *= v;
        }
        
        
        return answer - 1;
    }
}