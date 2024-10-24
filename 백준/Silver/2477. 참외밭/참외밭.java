import java.io.*;
import java.util.*;

public class Main {
    static int next = 0;
    static List<int[]> inputs = new ArrayList<>();
    static int xMax;
    static int yMax;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        xMax = 0;
        yMax = 0;
        for (int i = 0 ; i < 6 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int direct = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            inputs.add(new int[]{direct, length});
            if (direct == 1 || direct == 2) {
                xMax = Math.max(xMax, length);
            } else {
                yMax = Math.max(yMax, length);
            }
        }
        int idx = 0;
        while (true) {
            if (inputs.get(idx)[0] == 1 || inputs.get(idx)[0] == 2) {
                if (check(yMax, xMax, idx)) break;
                else {
                    inputs.add(inputs.remove(idx));
                }
            } else if (inputs.get(idx)[0] == 3 || inputs.get(idx)[0] == 4) {
                if (check(xMax, yMax, idx)) break;
                else {
                    inputs.add(inputs.remove(idx));
                }
            }
        }

        int width = solveLength();
        System.out.println(width * N);
    }

    private static int solveLength() {
        int rest = 0;
        if (next == 1) {
            rest = inputs.get(3)[1] * inputs.get(4)[1];
        } else {
            rest = inputs.get(2)[1] * inputs.get(3)[1];
        }
        return (yMax * xMax) - rest;
    }

    private static boolean check(int xMax, int yMax, int idx) {
        if (inputs.get(idx)[1] == yMax) {
            if (inputs.get(idx + 1)[1] == xMax) {
                next = 1;
                return true;
            } else if (inputs.get(inputs.size() - 1)[1] == xMax) {
                next = 2;
                return true;
            }
        }
        return false;
    }
}