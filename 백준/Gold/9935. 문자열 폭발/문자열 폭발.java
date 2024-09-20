import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        Stack<String> stk = new Stack<>();
        int idx = 0;
        StringBuilder sb = new StringBuilder();

        while (idx < str.length()) {
            String cur = String.valueOf(str.charAt(idx));
            boolean isBomb = true;
            stk.push(cur);
            idx++;
            if (stk.size() >= bomb.length() && stk.peek().equals(String.valueOf(bomb.charAt(bomb.length() - 1)))) {
                for (int i = 1 ; i <= bomb.length() ; i++) {
                    if (!stk.get(stk.size() - i).equals(String.valueOf(bomb.charAt(bomb.length() - i)))) {
                        isBomb = false;
                    }
                }
                if (isBomb) {
                    for (int i = 0 ; i < bomb.length() ; i++) {
                        stk.pop();
                    }
                }
            }
        }
        if (stk.isEmpty()) sb.append("FRULA");
        else {
            Stack<String> ans = new Stack<>();
            while (!stk.isEmpty()) {
                ans.push(stk.pop());
            }

            while (!ans.isEmpty()) {
                sb.append(ans.pop());
            }
        }
        System.out.println(sb.toString());
    }
}