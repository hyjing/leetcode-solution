// Refer to Java Related: Primitives vs. References
class Solution {
    public int lengthOfLongestSubstring(String s) {
        String longestStr = "";
        String newLongest = "";
        Map<Character, Integer> strMap = new HashMap<Character, Integer>();
        
        boolean repeat = false;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (strMap.get(currentChar) != null) {
                newLongest = newLongest.substring(newLongest.indexOf(currentChar) + 1);
                newLongest += currentChar;
                repeat = true;
            } else {
                if (!repeat) {
                    longestStr += currentChar;
                }
                newLongest += currentChar;
                strMap.put(currentChar, 1);
            }
            
            System.out.println(newLongest);
            if (newLongest.length() >= longestStr.length()) {
                
                longestStr = newLongest;
                repeat = false;
            }
        }
        
        return longestStr.length();
    }
}
