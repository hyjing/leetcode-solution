class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // topological order
        // order is unique and equals to org
        // two consecutive number in org must be in the same sequence in seqs:
        // ensure org is hamilton path
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();
        
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                map.putIfAbsent(seq.get(i), new ArrayList<>());
                indegrees.putIfAbsent(seq.get(i), 0);
                
                if (i > 0) {
                    map.get(seq.get(i - 1)).add(seq.get(i));
                    indegrees.put(seq.get(i), indegrees.get(seq.get(i)) + 1);
                }
            }
        }
        
        if (indegrees.size() != org.length) return false;
        
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }
        
        int i = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1) return false;
            
            int cur = q.poll();
            if (org[i++] != cur) return false;
            
            for (int next : map.get(cur)) {
                indegrees.put(next, indegrees.get(next) - 1);
                if (indegrees.get(next) == 0) {
                    q.add(next);
                }
            }
        }
        
        return i == org.length;
    }
}
