import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken()) - 1;

        List<Integer> lst = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(lst);

        int cnt = 0;
        int standard = lst.get(0);
        for (int i = 1 ; i < n ; i++) {
            if (lst.get(i) - standard > goal) {
                cnt++;
                standard = lst.get(i);
            }
        }
        System.out.println(cnt + 1);
    }
}
