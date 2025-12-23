import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int YEAR;
    static int[][] nutrients;
    static int[][] grounds;
    static int[] dy = new int[]{-1, 0, 1};
    static int[] dx = new int[]{-1, 0, 1};

    static Map<Integer, List<Integer>> trees = new HashMap<>();

    public static void main(String[] args) throws IOException {
        inputs();
        for (int i = 0 ; i < YEAR ; i++) {
            springAndSummer();
            autumn();
            winter();
        }
        int count = counting();
        System.out.println(count);
    }

    private static void springAndSummer() {
        List<Integer> keys = new ArrayList<>(trees.keySet());
//        System.out.println(keys.size());
        for (int key : keys) {
            List<Integer> ages = trees.get(key);
            List<Integer> newAge = new ArrayList<>();
            Collections.sort(ages);

            int y = key / N;
            int x = key % N;
            int deadNute = 0;

            int idx = ages.size();
            for (int i = 0 ; i < ages.size() ; i++) {
                Integer age  = ages.get(i);
//                System.out.println("key : " + key + ", age : " + age);
                if (grounds[y][x] < age) {
                    idx = i;
                    break;
                } else {
                    newAge.add(age + 1);
                    grounds[y][x] -= age;
                }
            }

            for (int i = idx ; i < ages.size() ; i++) {
                deadNute += ages.get(i) / 2;
            }

            grounds[y][x] += deadNute;
            if (newAge.isEmpty()) trees.remove(key);
            else trees.put(key, newAge);
        }
    }


    private static void autumn() {
        List<Integer> keys = new ArrayList<>(trees.keySet());
        Map<Integer, Integer> babies = new HashMap<>();
        for (Integer key : keys) {
            List<Integer> treesInArea = trees.get(key);
            Iterator<Integer> iterator = treesInArea.iterator();
            while (iterator.hasNext()) {
                Integer treeAge = iterator.next();
                if (treeAge != null && treeAge % 5 == 0) {
                    int y = key / N;
                    int x = key % N;

                    for (int i = 0 ; i < 3 ; i++) {
                        for (int j = 0 ; j < 3 ; j++) {
                            if (canPlant(y + dy[i], x + dx[j]) && !(i == 1 && j == 1)) {
                                Integer newKey = (y + dy[i]) * N + (x + dx[j]);
                                babies.merge(newKey, 1, Integer::sum);
                            }
                        }
                    }
                }
            }


        }

        Set<Integer> ks = babies.keySet();
        for (Integer k : ks) {
            int cnt = babies.get(k);
            trees.computeIfAbsent(k, kk -> new ArrayList<>());
            List<Integer> treeAges = trees.get(k);
            for (int i = 0 ; i < cnt ; i++) {
                treeAges.add(1);
            }
        }
    }


    private static void winter() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                grounds[i][j] += nutrients[i][j];
            }
        }
    }


    private static int counting() {
        int cnt = 0;
        Set<Integer> keys = trees.keySet();
        for (Integer key : keys) {
            List<Integer> treeAges = trees.get(key);
            for (int i = 0 ; i < treeAges.size() ; i++) {
                if (treeAges.get(i) != null) cnt++;
            }
        }

        return cnt;
    }

    private static boolean canPlant(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }

    private static void inputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        YEAR = Integer.parseInt(st.nextToken());

        grounds = new int[N][N];
        nutrients = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                nutrients[i][j] = Integer.parseInt(st.nextToken());
                grounds[i][j] = 5;
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            int key = N * y + x;
            trees.computeIfAbsent(key, k -> new ArrayList<>()).add(age);
        }
    }
}