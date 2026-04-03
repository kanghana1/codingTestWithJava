import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[][] notebook;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        notebook = new int[N][M];

        for (int k = 0 ; k < K ; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[i][j];
            for (int x = 0 ; x < i ; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0 ; y < j ; y++) {
                    sticker[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0 ; r < 4 ; r++) {
                if (attach(sticker)) {
                    break;
                }
                sticker = rotate(sticker);
            }
        }

        int count = 0;
        for (int i = 0 ; i < notebook.length ; i++) {
            for (int j = 0 ; j < notebook[0].length ; j++) {
                if (notebook[i][j] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static int[][] rotate(int[][] sticker) {
        int[][] rotated = new int[sticker[0].length][sticker.length];
        for (int i = 0 ; i < sticker.length ; i++) {
            for (int j = 0 ; j < sticker[0].length ; j++) {
                rotated[j][sticker.length - 1 - i] = sticker[i][j];
            }
        }
        return rotated;
    }

    private static boolean attach(int[][] sticker) {
        for (int i = 0 ; i < notebook.length ; i++) {
            for (int j = 0 ; j < notebook[0].length ; j++) {
                if (!canAttach(sticker, i, j)) continue;
                for (int y = 0 ; y < sticker.length ; y++) {
                    for (int x = 0 ; x < sticker[0].length ; x++) {
                        if (sticker[y][x] == 1) {
                            notebook[i + y][j + x] = 1;
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    private static boolean canAttach(int[][] sticker, int y, int x) {
        if (sticker.length + y > notebook.length || sticker[0].length + x > notebook[0].length) {
            return false;
        }

        for (int i = 0 ; i < sticker.length ; i++) {
            for (int j = 0 ; j < sticker[0].length ; j++) {
                if (sticker[i][j] == 1 && notebook[y + i][x + j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
