import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int init = parseInt(st.nextToken());
        String goal = st.nextToken();
        int cnt = 0;
        int a = 0;
        while (true) {
            if (parseInt(goal) == init) break;
            else if (parseInt(goal) < init || (goal.charAt(goal.length() - 1) != '1' && parseInt(goal) % 2 == 1) ) {
                cnt = -1;
                break;
            }
            if (goal.charAt(goal.length() - 1) == '1') {
                goal = goal.substring(0, goal.length() - 1);
                cnt++;
            }
            else if (parseInt(goal) % 2 == 0) {
                a = parseInt(goal) / 2;
                goal = String.valueOf(a);
                cnt++;
            }
        }
        if (init == Integer.parseInt(goal)) cnt++;
        System.out.println(cnt);
    }
}