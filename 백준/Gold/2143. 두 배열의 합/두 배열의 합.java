import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int[] arrA;
    static int[] arrB;
    static Map<Integer, Long> partSumA = new HashMap<>();
    static Map<Integer, Long> partSumB = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        fillArray(br);

        partSum(arrA);
        partSum(arrB);

        long ans = solve();
        System.out.println(ans);
    }

    private static long solve() {
        Set setA = partSumA.keySet();
        Set setB = partSumB.keySet();

        long ans = 0;
        for (Object a : setA) {
            Long cntA = partSumA.get(a);
            Long cntB = partSumB.get(T - (int) a);
            if (cntB != null) {
                ans += cntA * cntB;
            }
        }

        return ans;
    }

    private static void partSum(int[] arr) {
        for (int i = 0 ; i < arr.length ; i++) {
            for (int j = 0 ; j < arr.length - i ; j++) {
                int sum = 0;
                for (int k = j ; k <= j + i ; k++) {
                    sum += arr[k];
                }
                if (arr == arrA) {
                    partSumA.put(sum, partSumA.getOrDefault(sum, 0L) + 1);
                } else {
                    partSumB.put(sum, partSumB.getOrDefault(sum, 0L) + 1);
                }
            }
        }
    }

    private static void fillArray(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        arrA = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        arrB = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

    }
}