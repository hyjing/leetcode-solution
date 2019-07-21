// 621. Task Scheduler

// 1. Use a priority queue to have task with highest frequency at front, arrange each task one time and decrement empty slots relatively,
// if there are still empty slots it will require such number of idles until next round, otherwise no idle needed
// (since the n limit has already passed)
//	2. get all emptyslots and all available tasks, idles = Math.max(0, emptySlots - availableTasks);
// 	3. extension: 358. Rearrange String k Distance Apart

class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
            (a, b) -> a.getValue() == b.getValue() ? b.getKey() - a.getKey() : b.getValue() - a.getValue());
        
        queue.addAll(map.entrySet());
        int count = 0;
        
        while (!queue.isEmpty()) {
            int emptySlots = n + 1;
            List<Map.Entry> tempList = new ArrayList<>();
            
            while (emptySlots > 0 && !queue.isEmpty()) {
                Map.Entry<Character, Integer> task = queue.poll();
                emptySlots--;
                count++;
                task.setValue(task.getValue() - 1);
                if (task.getValue() > 0) {
                    tempList.add(task);
                }
            }
            
            for (Map.Entry task : tempList) {
                queue.add(task);
            }
            
            if (queue.isEmpty()) break;
            
            if (emptySlots > 0) {
                // idle
                count += emptySlots;
            }
        }
        
        return count;
    }
}
