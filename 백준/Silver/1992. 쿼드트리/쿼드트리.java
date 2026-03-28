import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        arr = new int[T + 1][T + 1];
        for (int i = 1 ; i <= T ; i++) {
            String str = br.readLine();
            for (int j = 1 ; j <= T ; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j - 1)));
            }
        }

        recursion(1, 1, T, T);
        System.out.println(sb);
    }


    private static void recursion(int startY, int startX, int endY, int endX) {
        if (startY == endY) {
            sb.append(arr[startY][startX]);
            return;
        }

        boolean allSame = allSame(startY, startX, endY, endX);
        if (allSame) {
            sb.append(arr[startY][startX]);
        } else {
            sb.append("(");
            int midX = (startX + endX) / 2;
            int midY = (startY + endY) / 2;
            recursion(startY, startX, midY, midX);
            recursion(startY, midX + 1, midY, endX);
            recursion(midY + 1, startX, endY, midX);
            recursion(midY + 1, midX + 1, endY, endX);
            sb.append(")");
        }
    }

    private static boolean allSame(int startY, int startX, int endY, int endX) {
        int value = arr[startY][startX];
        for (int i = startY ; i <= endY ; i++) {
            for (int j = startX ; j <= endX ; j++) {
                if (arr[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
