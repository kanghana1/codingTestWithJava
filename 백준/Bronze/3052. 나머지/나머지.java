import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            int a = Integer.parseInt(br.readLine()) % 42;
            if (!list.contains(a)) list.add(a);
        }
        System.out.println(list.size());
    }
}