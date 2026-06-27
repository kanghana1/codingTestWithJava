import java.util.*;

class Solution {
    
    static int endHour;
    static int endMinute;
    static int startHour;
    static int startMinute;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] lenArr = video_len.split(":");
        int finHour = Integer.parseInt(lenArr[0]);
        int finMinute = Integer.parseInt(lenArr[1]);
        
        String[] opStartArr = op_start.split(":");
        startHour = Integer.parseInt(opStartArr[0]);
        startMinute = Integer.parseInt(opStartArr[1]);
        
        String[] opEndArr = op_end.split(":");
        endHour = Integer.parseInt(opEndArr[0]);
        endMinute = Integer.parseInt(opEndArr[1]);
        
        String[] start = pos.split(":");
        int hour = Integer.parseInt(start[0]);
        int minute = Integer.parseInt(start[1]);
        
        for (String op : commands) {
            if (isOpening(hour, minute)) {
                hour = endHour;
                minute = endMinute;
            }
            
            System.out.println(hour + ":" + minute);
            
            if (op.equals("next")) {
                if (minute >= 50) hour++;
                minute = (minute + 10) % 60;
                
                if ((hour == finHour && minute > finMinute) || hour > finHour) {
                    hour = finHour;
                    minute = finMinute;
                }
                
            } else if (op.equals("prev")) {
                if (minute - 10 < 0) hour--;
                minute = (minute - 10 + 60) % 60;
                
                if (hour < 0) {
                    hour = 0;
                    minute = 0;
                }
            }
            
            
            System.out.println(hour + ":" + minute);
        }
        
        if (isOpening(hour, minute)) {
            hour = endHour;
            minute = endMinute;
        }

        return formatting(hour, minute);
    }
    
    private static String formatting(int h, int m) {
        StringBuilder sb = new StringBuilder();
        if (h > 9) sb.append(h);
        else sb.append(0).append(h);
        
        sb.append(":");
        
        if (m < 10) sb.append(0).append(m);
        else sb.append(m);
        
        return sb.toString();
    }
    
    private static boolean isOpening(int h, int m) {
        int cur = h * 60 + m;
        int s = startHour * 60 + startMinute;
        int e = endHour * 60 + endMinute;
        return cur >= s && cur <= e;
    }
}