import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int totalACount = aCount(str);

        char[] arr = makeArr(str, totalACount);
        int ans = search(arr, totalACount);
        System.out.println(ans);
    }

    private static char[] makeArr(String str, int totalACount) {
        char[] arr = new char[str.length() + totalACount];
        for (int i = 0 ; i < str.length() ; i++) {
            arr[i] = str.charAt(i);
        }
        for (int i = str.length() ; i < arr.length ; i++) {
            arr[i] = str.charAt(i - str.length());
        }

        return arr;
    }

    private static int search(char[] charArray, int totalACount) {
        int minSwaps = Integer.MAX_VALUE;
        for (int left = 0 ; left < charArray.length - totalACount ; left++) {
            int right = left + totalACount - 1;
            int swapCnt = bCountInBound(charArray, left, right);
            minSwaps = Math.min(minSwaps, swapCnt);
        }
        return minSwaps;
    }

    private static int bCountInBound(char[] charArray, int left, int right) {
        int count = 0;
        for (int i = left ; i <= right ; i++) {
            if (charArray[i] == 'b') {
                count++;
            }
        }
        return count;
    }


    private static int aCount(String charArray) {
        int count = 0;
        for (int i = 0 ; i < charArray.length() ; i++) {
            if (charArray.charAt(i) == 'a') {
                count++;
            }
        }
        return count;
    }
}
