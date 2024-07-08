import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        boolean check = true;
        int n = sc.nextInt();
        int[] arr = new int[n];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0 ; i < n ; i++) {
            arr[i] = i + 1;
        }
        int init = 1;
        int idx = -1;
        for (int j = 0 ; j < n ; j++) {
            int a = sc.nextInt();
            if (init <= a) {
                while (init <= a) {
                    idx++;
                    stk.push(arr[idx]);
                    sb.append("+");
                    sb.append("\n");
                    init = arr[idx];

                    if (init == a) {
                        stk.pop();
                        sb.append("-");
                        sb.append("\n");
                        break;
                    }
                }
            }
            else if (stk.peek() == a) {
                stk.pop();
                sb.append("-");
                sb.append("\n");
            }
            else check = false;
        }
        System.out.println(check ? sb.toString() : "NO");
    }
}
