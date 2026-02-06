import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int redLeft = moveRedLeft(str.toCharArray(), N);
        int redRight = moveRedRight(str.toCharArray(), N);
        int blueLeft = moveBlueLeft(str.toCharArray(), N);
        int blueRight = moveBlueRight(str.toCharArray(), N);
        System.out.println(Math.min(Math.min(redLeft, redRight), Math.min(blueLeft, blueRight)));

    }

    private static int moveBlueRight(char[] arr, int n) {
        int blueRight = 0;
        int lastBlueIdx = n;
        // blue move right
        for (int i = n - 1 ; i >= 0 ; i--) {
            if (arr[i] == 'B') {
                if (i == lastBlueIdx - 1) {
                    lastBlueIdx--;
                    continue;
                }
                swap(arr, i, lastBlueIdx - 1);
                lastBlueIdx--;
                blueRight++;
            }
        }
        return blueRight;
    }

    private static int moveBlueLeft(char[] arr, int n) {
        int blueLeft = 0;

        int lastBlueIdx = -1;
        // blue move left
        for (int i = 0 ; i < n ; i++) {
            if (arr[i] == 'B') {
                if (i == lastBlueIdx + 1) {
                    lastBlueIdx++;
                    continue;
                }
                swap(arr, i, lastBlueIdx + 1);
                lastBlueIdx++;
                blueLeft++;
            }
        }
        return blueLeft;
    }

    private static int moveRedLeft(char[] arr, int n) {
        int redLeft = 0;
        int lastRedIdx = -1;
        // red move left
        for (int i = 0 ; i < n ; i++) {
            if (arr[i] == 'R') {
                if (i == lastRedIdx + 1) {
                    lastRedIdx++;
                    continue;
                }
                swap(arr, i, lastRedIdx + 1);
                lastRedIdx++;
                redLeft++;
            }
        }

        return redLeft;
    }

    private static int moveRedRight(char[] arr, int n) {
        int redRight = 0;
        int lastRedIdx = n;
        // red move right
        for (int i = n - 1 ; i >= 0 ; i--) {
            if (arr[i] == 'R') {
                if (i == lastRedIdx - 1) {
                    lastRedIdx--;
                    continue;
                }
                swap(arr, i, lastRedIdx - 1);
                lastRedIdx--;
                redRight++;
            }
        }
        return redRight;
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
