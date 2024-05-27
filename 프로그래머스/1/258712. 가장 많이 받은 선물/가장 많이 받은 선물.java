import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        List<String> friend = Arrays.asList(friends);
        int[][] giveAndTake = new int[friends.length][friends.length];

        for (int i = 0 ; i < gifts.length ; i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            int give = friend.indexOf(st.nextToken());
            int take = friend.indexOf(st.nextToken());
            giveAndTake[give][take]++;
        }
        int[] score = new int[friends.length];
        for (int i = 0 ; i < friends.length ; i++) {
            for (int j = 0 ; j < i ; j++) {
                if (giveAndTake[i][j] == giveAndTake[j][i]) {
                    int a = getGiftIdx(i, giveAndTake);
                    int b = getGiftIdx(j, giveAndTake);
                    if (a > b) score[i]++;
                    else if (a < b) score[j]++;
                }
                else if (giveAndTake[i][j] > giveAndTake[j][i]) {
                    score[i]++;
                }
                else score[j]++;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < friends.length ; i++) {
            if (max < score[i]) max = score[i];
        }
        return max;
    }
    
    public int getGiftIdx(int friendIdx, int[][] arr) {
        int give = 0;
        int take = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            give += arr[friendIdx][i];
            take += arr[i][friendIdx];
        }
        return give - take;
    }
    
}