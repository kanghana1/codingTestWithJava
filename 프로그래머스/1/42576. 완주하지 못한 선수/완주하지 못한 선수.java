import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> maps = new HashMap<>();
        for (String str : participant) {
            maps.put(str, maps.getOrDefault(str, 0) + 1);
        }
        
        for (String str : completion) {
            if (maps.get(str) == 0) return str;
            maps.put(str, maps.get(str) - 1);
            if (maps.get(str) == 0) maps.remove(str);
        }
        
        for (String str : maps.keySet()) {
            return str;
        }
        return "";
        
    }
}