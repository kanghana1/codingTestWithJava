import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static String input;
    static List<Integer> idxStored = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        arr = new int[26];
        for (int i = 0 ; i < input.length() ; i++) {
            arr[input.charAt(i) - 65]++; // A = 0, B = 1 ..
        }
        String ans = check();
        System.out.println(ans);
    }
    public static String check() {
        int oddCnt = 0;
        int oddIdx = 0;
        char[] ans = new char[input.length()]; // 정답배열 생성

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] % 2 == 1) { // 홀수인 거 찾기 -> 홀수인개 1개 넘으면 불가능 / 홀수는 가운데에 넣기
                oddCnt++;
                oddIdx = i;
            }
            if (arr[i] > 0) idxStored.add(i);
        }
        if (oddCnt > 1) return "I'm Sorry Hansoo";

        if (oddCnt == 1) {
            ans[input.length() / 2] = (char)(oddIdx + 65);
            if (arr[oddIdx] == 1) {
                idxStored.remove((Integer) oddIdx);
            }
        }

        int index = 0;
        for (int i = 0 ; i < idxStored.size() ; i++) {
            int idx = idxStored.get(i); // 2번 이상 들어간 애의 인덱스
            for (int j = 0 ; j < arr[idx] / 2 ; j++) {
                ans[index] = (char)(idx + 65);
                ans[ans.length - 1 - index] = (char)(idx + 65);
                index++;
            }
        }
        return String.copyValueOf(ans);
    }
}
