import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int len = Integer.parseInt(br.readLine());
        int oiCnt = 0;
        int ans = 0;
        String str = br.readLine();

        for (int i = 1 ; i < len - 1 ;) {
            if (str.charAt(i) == 'O' && str.charAt(i + 1) == 'I') {
                oiCnt++;
                if (oiCnt == N) {
                    if (str.charAt(i - (oiCnt * 2 - 1)) == 'I') ans++;
                    oiCnt--;
                }
                i += 2;
            } else {
                oiCnt = 0;
                i++;
            }
        }
        System.out.println(ans);
    }
}