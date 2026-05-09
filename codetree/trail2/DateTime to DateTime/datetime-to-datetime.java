import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int day = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        if (min < 11) {
            time--;
        }
        if (time < 11) day--;
        
        min = (min + 60 - 11) % 60;
        time = (time + 24 - 11) % 24;
        day = day - 11;

    
        if (day < 0 || day == 0 && time < 0 || day == 0 && time == 0 && min < 0) System.out.print(-1);
        else {
            System.out.print(day * 24 * 60 + time * 60 + min);
        }
    }
}