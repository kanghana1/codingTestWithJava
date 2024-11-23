import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0 ; i < N ; i++) {
            String str = br.readLine();
            boolean check = true;
            int[] alpha = new int[26];
            alpha[str.charAt(0) - 'a'] = 1;
            char std = str.charAt(0);

            for (int j = 1 ; j < str.length() ; j++) {
                if (std != str.charAt(j) && alpha[str.charAt(j) - 'a'] != 0) {
                    check = false;
                    break;
                }
                std = str.charAt(j);
                alpha[std - 'a']++;
            }
            if (check) cnt++;
        }
        System.out.println(cnt);
    }
}