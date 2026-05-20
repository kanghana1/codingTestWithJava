import java.util.*;
class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        long maxTime = 1; // 주기
        
        for (int[] signal : signals) {
            long T = signal[0] + signal[1] + signal[2];
            maxTime = lcm(maxTime, T);
        }
        
        for (int t = 1; t <= maxTime; t++) {
            boolean allYellow = true;
            
            for (int[] signal : signals) {
                int G = signal[0];
                int Y = signal[1];
                int R = signal[2];
                int T = G + Y + R;
                
                int remain = (t - 1) % T;
                
                if (remain < G || remain >= G + Y) {
                    allYellow = false;
                    break;
                }
            }
            
            if (allYellow) {
                return t;
            }
        }
        
        return -1;
    }
    

    public long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
     return a;
    }

    public long lcm(long a, long b) {
       return (a * b) / gcd(a, b);
    }
}