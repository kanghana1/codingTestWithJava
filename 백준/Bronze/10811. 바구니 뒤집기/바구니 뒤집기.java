import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0 ; i < n ; i++) {
            arr[i] = i + 1;
        }
        for (int k = 0 ; k < c ; k++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int cnt = (Math.abs(a - b) + 1) / 2;
            for (int j = 0 ; j < cnt ; j++) {
                int bucket = arr[a];
                arr[a] = arr[b];
                arr[b] = bucket;

                if (a > b) {
                    a--;
                    b++;
                }
                else {
                    a++;
                    b--;
                }
            }
        }
        for (int x = 0 ; x < n ; x++) {
            System.out.print(arr[x] + " ");
        }
    }
}
