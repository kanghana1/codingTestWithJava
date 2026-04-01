import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int[] first = new int[8];

    static int[] second = new int[8];

    static int[] third = new int[8];

    static int[] fourth = new int[8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fillArray(br);

        int T = Integer.parseInt(br.readLine());
        for (int t = 0 ; t < T ; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int obj = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()); // -1: 반시계, 1: 시계

             if (obj == 1) {
                rotate(1, dir);
            } else if (obj == 2) {
                rotate(2, dir);
            } else if (obj == 3) {
                rotate(3, dir);
            } else {
                rotate(4, dir);
            }
        }

        int score = score();
        System.out.println(score);
    }

    private static int score() {
        int sum = 0;
        if (first[0] == 1) {
            sum += 1;
        }
        if (second[0] == 1) {
            sum += 2;
        }
        if (third[0] == 1) {
            sum += 4;
        }
        if (fourth[0] == 1) {
            sum += 8;
        }
        return sum;
    }

    private static void rotate(int obj, int dir) {
        if (obj == 1) {
            if (first[2] != second[6]) {
                if (second[2] != third[6]) {
                    if (third[2] != fourth[6]) {
                        rotateArrayValue(4, -dir);
                    }
                    rotateArrayValue(3, dir);
                }
                rotateArrayValue(2, -dir);
            }
            rotateArrayValue(obj, dir);

        } else if (obj == 2) {
            if (second[6] != first[2]) {
                rotateArrayValue(1, -dir);
            }
            if (second[2] != third[6]) {
                if (third[2] != fourth[6]) {
                    rotateArrayValue(4, dir);
                }
                rotateArrayValue(3, -dir);
            }
            rotateArrayValue(obj, dir);
        } else if (obj == 3) {
            if (third[6] != second[2]) {
                if (second[6] != first[2]) {
                    rotateArrayValue(1, dir);
                }
                rotateArrayValue(2, -dir);
            }
            if (third[2] != fourth[6]) {
                rotateArrayValue(4, -dir);
            }
            rotateArrayValue(obj, dir);
        } else {
            if (fourth[6] != third[2]) {
                if (third[6] != second[2]) {
                    if (second[6] != first[2]) {
                        rotateArrayValue(1, -dir);
                    }
                    rotateArrayValue(2, dir);
                }
                rotateArrayValue(3, -dir);
            }
            rotateArrayValue(obj, dir);
        }
    }

    private static void rotateArrayValue(int obj, int dir) {
        if (obj == 1) {
            if (dir == 1) {
                int temp = first[7];
                for (int i = 7 ; i > 0 ; i--) {
                    first[i] = first[i - 1];
                }
                first[0] = temp;
            } else {
                int temp = first[0];
                for (int i = 0 ; i < 7 ; i++) {
                    first[i] = first[i + 1];
                }
                first[7] = temp;
            }
        } else if (obj == 2) {
            if (dir == 1) {
                int temp = second[7];
                for (int i = 7 ; i > 0 ; i--) {
                    second[i] = second[i - 1];
                }
                second[0] = temp;
            } else {
                int temp = second[0];
                for (int i = 0 ; i < 7 ; i++) {
                    second[i] = second[i + 1];
                }
                second[7] = temp;
            }
        } else if (obj == 3) {
            if (dir == 1) {
                int temp = third[7];
                for (int i = 7 ; i > 0 ; i--) {
                    third[i] = third[i - 1];
                }
                third[0] = temp;
            } else {
                int temp = third[0];
                for (int i = 0 ; i < 7 ; i++) {
                    third[i] = third[i + 1];
                }
                third[7] = temp;
            }
        } else {
            if (dir == 1) {
                int temp = fourth[7];
                for (int i = 7 ; i > 0 ; i--) {
                    fourth[i] = fourth[i - 1];
                }
                fourth[0] = temp;
            } else {
                int temp = fourth[0];
                for (int i = 0 ; i < 7 ; i++) {
                    fourth[i] = fourth[i + 1];
                }
                fourth[7] = temp;
            }
        }
    }

    private static void fillArray(BufferedReader br) throws IOException {
        for (int i = 0 ; i < 4;  i++) {
            String str = br.readLine();
            for (int j = 0 ; j < 8 ; j++) {
                int num = str.charAt(j) - '0';
                if (i == 0) {
                    first[j] = num;
                } else if (i == 1) {
                    second[j] = num;
                } else if (i == 2) {
                    third[j] = num;
                } else {
                    fourth[j] = num;
                }
            }
        }
    }
}
