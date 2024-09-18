import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < T ; i++) {
            String comm = br.readLine();
            int length = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            boolean isError = false;

            if (length > 0) {
                String[] input = arr.substring(1, arr.length() - 1).split(",");
                for (int j = 0 ; j < input.length ; j++) {
                    deque.addLast(Integer.valueOf(input[j]));
                }

                boolean isFront = true;
                for (int j = 0 ; j < comm.length() ; j++) {
                    if (comm.charAt(j) == 'R') {
                        if (isFront) isFront = false;
                        else isFront = true;
                    } else {
                        if (deque.isEmpty()) {
                            sb.append("error").append("\n");
                            isError = true;
                            break;
                        }
                        if (isFront) deque.removeFirst();
                        else deque.removeLast();
                    }
                }

                if (isError) continue;
                sb.append("[");
                while (!deque.isEmpty()) {
                    if (isFront) sb.append(deque.pollFirst()).append(",");
                    else sb.append(deque.pollLast()).append(",");
                }
                if (sb.charAt(sb.length() - 1) == ',') sb.deleteCharAt(sb.length() - 1);
                sb.append("]\n");
            } else {
                if (comm.contains("D")) sb.append("error\n");
                else sb.append("[]\n");
            }
        }
        System.out.println(sb.toString());
    }
}