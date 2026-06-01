import java.util.*;
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0 ; i < schedules.length ; i++) {
            int time = schedules[i] / 100;
            int minute = (schedules[i] % 100) + 10;
            if (minute >= 60) {
                minute -= 60;
                time++;
            }
            int deadline = time * 100 + minute;
            boolean canGet = true;
            for (int j = 0 ; j < timelogs[i].length ; j++) {
                if ((j + startday) % 7 == 6 || (j + startday) % 7 == 0) continue;
                if (deadline < timelogs[i][j]) {
                    canGet = false;
                    break;
                }
            }
            if (canGet) answer++;
        }
        return answer;
    }
}