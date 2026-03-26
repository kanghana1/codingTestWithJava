import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String ans = drawStar(N);
        System.out.println(ans);
    }

    private static String drawStar(int N) {
        if (N == 1) {
            return "*";
        }

        StringBuilder sb = new StringBuilder();
        String prev = drawStar(N / 3);
        String empty = " ".repeat(N / 3);

        String[] lines = prev.split("\n");

        for (String line : lines) {
            sb.append(line).append(line).append(line).append("\n");
        }

        for (String line : lines) {
            sb.append(line).append(empty).append(line).append("\n");
        }

        for (String line : lines) {
            sb.append(line).append(line).append(line).append("\n");
        }

        return sb.toString().stripTrailing();
    }
}
