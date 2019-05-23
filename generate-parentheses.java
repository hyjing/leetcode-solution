// Backtrack algorithm in recursion
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        generateRecur(list, "", 0, 0, n);
        return list;
    }
    
    public void generateRecur(List<String> list, String s, int open, int close, int n) {
        if (s.length() == n * 2) {
            list.add(s);
            return;
        }
        
        if (open < n) {
            generateRecur(list, s + "(", open + 1, close, n);
        }
        if (open > close) {
            generateRecur(list, s + ")", open, close + 1, n);
        }
    }
}
