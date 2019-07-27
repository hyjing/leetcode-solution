// 994. Rotting Oranges
// bfs
class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int fresh_count = 0;
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    fresh_count++;
                }
            }
        }
        
        if (fresh_count == 0) return 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] rotten = queue.poll();
                for (int[] dir : dirs) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];
                    
                    if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[x].length - 1 || grid[x][y] == 2 || grid[x][y] == 0) {
                        continue;
                    }
                    
                    grid[x][y] = 2;
                    queue.add(new int[] {x, y});
                    fresh_count--;
                }
            }
        }
        
        return fresh_count == 0 ? count - 1 : -1;
    }
}
