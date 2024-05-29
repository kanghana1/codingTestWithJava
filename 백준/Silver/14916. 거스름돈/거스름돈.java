import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int five = 0;
        int two = 0;
        if (n >= 10) {
            if (n % 2 == 0) {
                five = (n / 10 * 2);
                two = (n - five * 5) / 2;
            }
            else {
                if (n % 10 >= 5) {
                    five = n / 5;
                    two = (n - five * 5) / 2;
                }
                else {
                    five = n / 5 - 1;
                    two = (n - five * 5) / 2;
                }
            }
        }
        else {
            if (n % 2 == 0) two = n / 2;
            else {
                if (n >= 5) {
                    five = 1;
                    two = (n - 5) / 2;
                }
                else {
                    five = -1;
                }
            }
        }
        System.out.println(five + two);

    }
}