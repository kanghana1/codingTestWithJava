import java.util.*;
import java.io.*;
public class Main {
    static int[] perMonthsDay = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] dayOfWeek = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());

        int days = 0;
        if (m1 == m2 && d1 > d2 || m1 > m2) {
            // past
            if (m1 == m2) days = d1 - d2;
            else {
                days += d1;
                for (int m = m1 - 1 ; m >= m2 ; m--) {
                    if (m == m2) days += perMonthsDay[m] - d2;
                    else days += perMonthsDay[m];
                }
            }
            System.out.print(dayOfWeek[0 - (days % 7) + 7]);

        } else {
            // future
            if (m1 == m2) days = d2 - d1;
            else {
                days += (perMonthsDay[m1] - d1);
                for (int m = m1 + 1 ; m <= m2 ; m++) {
                    if (m == m2) days += d2;
                    else days += perMonthsDay[m];
                }
            }
            System.out.print(dayOfWeek[0 + (days % 7)]);
        }
    }
}