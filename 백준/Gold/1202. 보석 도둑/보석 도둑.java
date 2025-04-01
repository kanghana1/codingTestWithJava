import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Jw> lst = new ArrayList<>();

        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            Jw j = new Jw();
            j.weight = Integer.parseInt(st.nextToken());
            j.price = Integer.parseInt(st.nextToken());
            lst.add(j);
        }

        // w,p
        Collections.sort(lst, new Comparator<Jw>() {
            @Override
            public int compare(Jw o1, Jw o2) {
                if (o1.weight == o2.weight) return o2.price - o1.price;
                return o1.weight - o2.weight;
            }
        });
        long sol = 0;
        int[] bags = new int[k];
        for (int i = 0 ; i < k ; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        int idx = 0;
        PriorityQueue<Integer> p = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0 ; i < k ; i++) {
            while (idx < n && lst.get(idx).weight <= bags[i]) {
                p.offer(lst.get(idx).price);
                idx++;
            }
            if (!p.isEmpty()) sol += p.poll();
        }
        System.out.println(sol);
    }

    static class Jw {
        int weight;
        int price;
        public void Jw(int w, int p) {
            this.weight = w;
            this.price = p;
        }
    }
}