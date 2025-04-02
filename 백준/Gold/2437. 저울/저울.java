import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> lst = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            lst.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(lst);

        long sum = 0;
        long ans = 0;
        for (int i = 0 ; i < N ; i++) {
            if (sum + 1 < lst.get(i)) {
                ans = sum + 1;
                break;
            }
            sum += lst.get(i);
        }
        if (ans == 0) ans = sum + 1;
        System.out.println(ans);
    }
}
