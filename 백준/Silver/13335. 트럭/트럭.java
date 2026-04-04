import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int[] bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int bridgeLength = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> trucks = new LinkedList<>();
        for (int i = 0 ; i < N ; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        int ans = solve(trucks, bridgeLength, maxWeight);
        System.out.println(ans);
    }

    private static int solve(Queue<Integer> trucks, int bridgeLength, int maxWeight) {
        bridge = new int[bridgeLength];
        int onBridgeWeight = 0;
        int time = 0;
        while (!trucks.isEmpty()) {
            if (onBridgeWeight + trucks.peek() - bridge[0] <= maxWeight) {
                int truck = trucks.poll();
                onBridgeWeight += truck;
                onBridgeWeight -= moveTruck(truck);
            } else {
                onBridgeWeight -= moveTruck(0);
            }
            time++;
        }

        for (int i = bridgeLength - 1 ; i >= 0 ; i--) {
            if (bridge[i] != 0) {
                time += (i + 1);
                break;
            }
        }
        return time;
    }

    private static int moveTruck(int truck) {
        int outTruck = bridge[0];
        for (int i = 0 ; i < bridge.length - 1 ; i++) {
            bridge[i] = bridge[i + 1];
        }
        bridge[bridge.length - 1] = truck;
        return outTruck;
    }
}
