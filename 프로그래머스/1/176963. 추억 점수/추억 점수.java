import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> maps = new HashMap<>();
        for (int i = 0 ; i < name.length ; i++) {
            maps.put(name[i], yearning[i]);
        }
        
        int[] answer = new int[photo.length];
        for (int i = 0 ; i < photo.length ; i++) {
            int sum = 0;
            for (int j = 0 ; j < photo[i].length ; j++) {
                if (maps.get(photo[i][j]) == null) continue;
                sum += maps.get(photo[i][j]);
            }
            answer[i] = sum;
        }
        return answer;
    }
}