import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] isPresent = new int[21];
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int val = 0;
            if (!(cmd.equals("all") || cmd.equals("empty"))) {
                val = Integer.parseInt(st.nextToken());
            }

            if (cmd.equals("add")) isPresent[val] = 1;
            else if (cmd.equals("check")) sb.append(isPresent[val]).append("\n");
            else if (cmd.equals("remove")) isPresent[val] = 0;
            else if (cmd.equals("toggle")) {
                if (isPresent[val] == 1) isPresent[val] = 0;
                else isPresent[val] = 1;
            }
            else if (cmd.equals("all")) Arrays.fill(isPresent, 1);
            else Arrays.fill(isPresent, 0);
        }
        System.out.println(sb.toString());
    }
}