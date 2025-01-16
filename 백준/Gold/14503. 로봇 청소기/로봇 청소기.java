import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = new int[]{-1, 0, 1, 0, -1, 0, 1, 0}; // 북 서 남 동
    static int[] dx = new int[]{0, -1, 0, 1, 0, -1, 0, 1};
    static boolean[][] isClean;
    static boolean[][] isWall;
    static int Y;
    static int X;
    static List<Integer> space = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int direct = Integer.parseInt(st.nextToken());
        if (direct == 1) direct += 2;
        else if (direct == 3) direct -= 2;
        
        isClean = new boolean[Y][X];
        isWall = new boolean[Y][X];
        for (int i = 0 ; i < Y ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < X ; j++) {
                int wall = Integer.parseInt(st.nextToken());
                if (wall == 1) isWall[i][j] = true;
            }
        }

        int cnt = 0;
        while (true) {
            if (!isClean[startY][startX]) {
                isClean[startY][startX] = true;
                cnt++;
            }
            boolean allClean = true;
            for (int i = direct + 1 ; i <= direct + 4 ; i++) {
                if (canGo(startY + dy[i], startX + dx[i]) && !isClean[startY + dy[i]][startX + dx[i]]) {
                    allClean = false;
                    startY = startY + dy[i];
                    startX = startX + dx[i];
                    direct = i % 4;
                    break;
                }
            }
            if (allClean) {
                if(isWall[startY + dy[direct + 2]][startX + dx[direct + 2]]) break;
                else {
                    startY = startY + dy[direct + 2];
                    startX = startX + dx[direct + 2];
                }
            }
        }
        System.out.println(cnt);
    }

    private static boolean canGo(int i, int j) {
        return i >= 0 && i < Y && j >= 0 && j < X && !isWall[i][j];
    }
}