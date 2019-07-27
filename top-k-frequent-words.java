// 692. Top K Frequent Words
// similar questions: 215, 347, 451, 973
// sort/max heap/quick select/bucket sort
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<String> q = new PriorityQueue<String>((a, b) -> map.get(a) == map.get(b) ? b.compareTo(a) : map.get(a) - map.get(b));
        for (String word : map.keySet()) {
            q.offer(word);
            if (q.size() > k) {
                q.poll();
            }
        }
        
        List<String> res = new ArrayList<>();
        while (!q.isEmpty()) {
            res.add(0, q.poll());
        }
        return res;
    }
}
