import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] alphabet = new boolean[26];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<String> words = new ArrayList<>();

            int wordIdx = -1;
            int charIdx = -1;

            // 단어별로 첫 글자 확인 -> 리스트에 단어 저장, 첫글자에서 나올 시 인덱스 초기화
            int idx = 0;
            while (st.countTokens() != 0) {
                String word = st.nextToken();
                words.add(word);

                if (wordIdx == -1 && !alphabet[word.toLowerCase().charAt(0) - 'a']) {
                    alphabet[word.toLowerCase().charAt(0) - 'a'] = true;
                    wordIdx = idx;
                    charIdx = 0;
                }

                idx++;
            }

            // 첫 단어 검사에서 실패한 경우
            if (wordIdx == -1) {
                idx = 0;
                while (idx < words.size()) {
                    if (wordIdx != -1) break;
                    String word = words.get(idx);
                    for (int j = 1 ; j < word.length() ; j++) {
                        if (!alphabet[word.toLowerCase().charAt(j) - 'a']) {
                            alphabet[word.toLowerCase().charAt(j) - 'a'] = true;
                            wordIdx = idx;
                            charIdx = j;
                            break;
                        }
                    }
                    idx++;
                }
            }

            make(wordIdx, charIdx, words);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void make(int wordIdx, int charIdx, List<String> words) {
        if (wordIdx == -1) {
            for (String word : words) {
                sb.append(word + " ");
            }
            return;
        }

        for (int i = 0 ; i < words.size() ; i++) {
            if (wordIdx != i) {
                sb.append(words.get(i) + " ");
            } else {
                String word = words.get(i);
                if (charIdx == 0) {
                    sb.append("[" + word.charAt(0) + "]" + word.substring(1) + " ");
                } else {
                    sb.append(word.substring(0, charIdx) + "[" + word.charAt(charIdx) + "]" + word.substring(charIdx + 1) + " ");
                }
            }
        }
    }
}
