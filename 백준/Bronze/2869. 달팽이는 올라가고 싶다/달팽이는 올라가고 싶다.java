import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        int totalD = goal - b;
        int canD = a - b;

        int day = totalD / canD;
        if (totalD % canD > 0) day++;
        System.out.println(day);

    }
}