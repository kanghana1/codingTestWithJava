class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int l = Math.max(bill[0], bill[1]);
        int s = Math.min(bill[0], bill[1]);
        
        while(true) {
            if (canPut(l, s, wallet)) {
                break;
            }
            
            if (l / 2 > s) {
                l = l / 2;
            } else {
                int ll = l / 2;
                l = s;
                s = ll;
            }
            
            answer++;
            
        }
        return answer;
    }
    
    private static boolean canPut(int l, int s, int[] wallet) {
        return (wallet[0] >= l && wallet[1] >= s) || (wallet[0] >= s && wallet[1] >= l);
    }
}