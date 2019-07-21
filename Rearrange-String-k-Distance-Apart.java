// 358. Rearrange String k Distance Apart
class Solution {
    public String rearrangeString(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int[] valid = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        // fill characters from 0 to end of string
        for (int i = 0; i < len; i++) {
            int candidateChar = findMaxLeft(count, valid, i);
            if (candidateChar == -1) return "";
            valid[candidateChar] = i + k;
            count[candidateChar]--;
            sb.append((char) ('a' + candidateChar));
        }
        
        return sb.toString();
    }
    
    public int findMaxLeft(int[] count, int[] valid, int index) {
        int max = Integer.MIN_VALUE;
        int candidateChar = -1;
        
        // for each character check its left most position possible
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && index >= valid[i] && count[i] > max) {
                max = count[i];
                candidateChar = i;
            }
        }
        
        return candidateChar;
    }
}
