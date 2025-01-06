import java.io.*;
import java.util.*;

// 1092
public class Main {
    static int[] set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> cranes = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            cranes.add(Integer.parseInt(st.nextToken()));
        }
        int M = Integer.parseInt(br.readLine());
        List<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < M ; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }
        boxes.sort(Collections.reverseOrder());
        cranes.sort(Collections.reverseOrder());

        move(cranes, boxes);
    }

    private static void move(List<Integer> cranes, List<Integer> boxes) {
        int day = 0;
        if (cranes.get(0) < boxes.get(0)) {
            System.out.println(-1);
            return;
        }
        while(!boxes.isEmpty()) {
            int boxIdx = 0;
            int craneIdx = 0;

            while (craneIdx < cranes.size()) {
                if (boxIdx == boxes.size()) break;
                if (cranes.get(craneIdx) >= boxes.get(boxIdx)) {
                    boxes.remove(boxIdx);
                    craneIdx++;
                } else {
                    boxIdx++;
                }
            }
            day++;
        }
        System.out.println(day);
    }
}