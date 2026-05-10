import java.util.*;
import java.io.*;
public class Main {
    static int[] monthsDay = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());
        String dayOfWeek = br.readLine();

        int days = 0;
        if (m1 == m2) days += (d2 - d1);
        else {
            days += (monthsDay[m1] - d1);
            for (int m = m1 + 1 ; m <= m2 ; m++) {
                if (m == m2) days += d2;
                else {
                    days += monthsDay[m];
                }
            }
        }

        days -= dayOfWeeks(days, dayOfWeek);
        int ans = 0;
        if (days < 0) ans = 0;
        else ans = days / 7 + 1;
        System.out.print(ans);
    }

    public static int dayOfWeeks(int d, String dw) {
        if (dw.equals("Mon")) return 0;
        else if (dw.equals("Tue")) return 1;
        else if (dw.equals("Wed")) return 2;
        else if (dw.equals("Thu")) return 3;
        else if (dw.equals("Fri")) return 4;
        else if (dw.equals("Sat")) return 5;
        else if (dw.equals("Sun")) return 6;
        return -1;
    }
}