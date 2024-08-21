class Solution {
    public String[] solution(String[] picture, int k) {
        int size = picture.length;
        String[] answer = new String[size * k];
        for (int i = 0 ; i < size ; i++) {
            String str = picture[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0 ; j < str.length() ; j++) {
                for (int p = 0 ; p < k ; p++) {
                    sb.append(str.charAt(j));
                }
            }
            
            for (int j = i * k ; j < i * k + k ; j++) {
                answer[j] = sb.toString();
            }
            sb.setLength(0);
            
        }
        return answer;
    }
}