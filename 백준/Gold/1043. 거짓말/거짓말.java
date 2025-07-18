import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티 수
        
        // 비밀 아는사람 입력받기
        st = new StringTokenizer(br.readLine());
        int knowNum = Integer.parseInt(st.nextToken());
        int[] knowMember = new int[knowNum];
        for (int i = 0 ; i < knowNum ; i++) {
            int know = Integer.parseInt(st.nextToken());
            knowMember[i] = know;
        }
        
        // 파티별 참가자 입력받기, 사람별 같은 파티에 참여하는 사람 담기
        List<List<Integer>> parties = new ArrayList<>();
        List<List<Integer>> person = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            person.add(new ArrayList<>());
        }
        for (int i = 0 ; i < M ; i++) {
            parties.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int participantNum = Integer.parseInt(st.nextToken());
            for (int j = 0 ; j < participantNum ; j++) {
                int participant = Integer.valueOf(st.nextToken());
                parties.get(i).add(participant);
                if (j >= 1) {
                    person.get(parties.get(i).get(0)).add(participant);
                    person.get(participant).add(parties.get(i).get(0));
                }
            }
        }

        // 소문 아는 사람 체크
        boolean[] knows = whoKnowRumor(knowMember, person);
        int cnt = 0;
        for (int i = 0 ; i < M ; i++) {
            if (!knows[parties.get(i).get(0)]) cnt++;
        }
        System.out.println(cnt);
    }
    
    private static boolean[] whoKnowRumor(int[] knows, List<List<Integer>> person) {
        boolean[] know = new boolean[person.size()]; // 소문 아는사람 true
        Queue<Integer> queue = new ArrayDeque<>();
        for (int knowOne : knows) {
            queue.add(knowOne);
        }

        while (!queue.isEmpty()) {
            Integer one = queue.poll();
            know[one] = true;

            for (int next : person.get(one)) {
                if (!know[next]) {
                    queue.add(next);
                }
            }
        }

        return know;
    }
}
