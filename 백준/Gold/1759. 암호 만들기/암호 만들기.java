import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
    static char[] inputs;
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static StringBuilder sb = new StringBuilder(); // a c i s t w
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        inputs = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < C ; i++) {
            inputs[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(inputs);
        findPw(new char[L], -1, 0, 0, L);
        System.out.println(sb);
    }

    private static void findPw(char[] now, int lastCharIdx, int nowSize, int cntVowel, int maxSize) {
        if (nowSize == maxSize && cntVowel > 0 && (maxSize - cntVowel >= 2)) {
            for (char c : now) {
                sb.append(c);
            }
            sb.append("\n");
            return;
        } else if (nowSize == maxSize) return;

        if (lastCharIdx == inputs.length - 1) return;
        for (int i = lastCharIdx + 1 ; i < inputs.length ; i++) {
            boolean contains = false;
            for (int j = 0 ; j < nowSize ; j++) {
                if (now[j] == inputs[i]) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                now[nowSize] = inputs[i];
                if (isVowel(inputs[i])) findPw(now, i, nowSize + 1, cntVowel + 1, maxSize);
                else findPw(now, i, nowSize + 1, cntVowel, maxSize);
            }
        }
    }

    private static boolean isVowel(char input) {
        for (char c : vowels) {
            if (input == c) return true;
        }
        return false;
    }
}