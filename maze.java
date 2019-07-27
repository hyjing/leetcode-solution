// 490. The Maze
// 505. The Maze II
// 499. The Maze III
// dfs/bfs

// maze 1
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) return false;
        int m = maze.length;
        int n = maze[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        return dfs(maze, new boolean[m][n], directions, start, destination);
    }
    
    public boolean dfs(int[][] maze, boolean[][] seen, int[][] directions, int[] start, int[] destination) {
        if (!!seen[start[0]][start[1]]) return false;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        seen[start[0]][start[1]] = true;
        
        for (int[] direction : directions) {
            int rInc = direction[0];
            int cInc = direction[1];
            int x = start[0];
            int y = start[1];
            if (direction[0] == 0) {
                while (y + cInc >= 0 && y + cInc < maze[0].length && maze[x][y + cInc] != 1) {
                    y += cInc;
                }
            } else {
                while (x + rInc >= 0 && x + rInc < maze.length && maze[x + rInc][y] != 1) {
                    x += rInc;
                }
            }
            
            if (dfs(maze, seen, directions, new int[] {x, y}, destination)) return true;
        }
        
        return false;
    }
}

// maze 2
class Solution {
    int[] direcs = new int[] {0, 1, 0, -1, 0};
//     dfs method
//     public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//         if (maze == null || maze.length == 0) return -1;
//         int m = maze.length, n = maze[0].length;
//         int[][] dp = new int[m][n];
//         dp[start[0]][start[1]] = 1;
//         dfs(maze, dp, start[0], start[1], destination);
//         return dp[destination[0]][destination[1]] - 1;
//     }
    
//     public void dfs(int[][] maze, int[][] dp, int row, int col, int[] destination) {
//         if (row == destination[0] && col == destination[1]) return;
//         int m = maze.length, n = maze[0].length;
        
//         for (int d = 0; d < 4; d++) {
//             int i = row, j = col, len = dp[row][col], p = direcs[d], q = direcs[d + 1];
//             while (i + p >= 0 && j + q >= 0 && i + p < m && j + q < n && maze[i + p][j + q] != 1) {
//                 i += p;
//                 j += q;
//                 len++;
//             }
            
//             if (dp[i][j] > 0 && len >= dp[i][j]) continue;
//             dp[i][j] = len;
//             dfs(maze, dp, i, j, destination);
//         }
//     }
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) return -1;
        int m = maze.length, n = maze[0].length;
        int[][] dp = new int[m][n];
        for (int[] temp : dp) {
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        dp[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[0], y = curr[1];
                int len = dp[x][y], p = direcs[d], q = direcs[d + 1];
                while (x + p >= 0 && y + q >= 0 && x + p < m && y + q < n && maze[x + p][y + q] != 1) {
                    x += p;
                    y += q;
                    len++;
                }
                
                if (len > dp[destination[0]][destination[1]]) continue;
                if (len < dp[x][y]) {
                    dp[x][y] = len;
                    queue.offer(new int[] {x, y});
                } else {
                    continue;
                }
            }
        }
        
        return dp[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dp[destination[0]][destination[1]];
    }
}

// maze 3
class Solution {
    int[] dir = new int[] {0, 1, 0, -1, 0};
    char[] dirChar = new char[] {'r', 'd', 'l', 'u'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0) return "impossible";
        if (ball[0] == hole[0] && ball[1] == hole[1]) return "";
        int m = maze.length;
        int n = maze[0].length;
        
        int[][] dp = new int[m][n];
        for (int[] temp : dp) {
            Arrays.fill(temp, Integer.MAX_VALUE);
        }
        dp[ball[0]][ball[1]] = 0;
        
        String[][] res = new String[m][n];
        for (String[] temp : res) {
            Arrays.fill(temp, "z");
        }
        res[ball[0]][ball[1]] = "";
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {ball[0], ball[1]});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int x = cur[0], y = cur[1];
                int len = dp[x][y], p = dir[d], q = dir[d + 1];
                StringBuilder sb = new StringBuilder(res[x][y]);

                sb.append(dirChar[d]);
                while (x + p >= 0 && y + q >= 0 && x + p < m && y + q < n && maze[x + p][y + q] != 1) {
                    x += p;
                    y += q;
                    len++;
                    if (x == hole[0] && y == hole[1]) break;
                }
                if (x == cur[0] && y == cur[1]) continue;
                if (len <= dp[x][y]) {
                    String str = sb.toString();
                    if (len < dp[x][y]) {
                        dp[x][y] = len;
                        res[x][y] = str;
                    } else if (str.compareTo(res[x][y]) < 0) {
                        res[x][y] = str;
                    }
                    queue.offer(new int[] {x, y});
                }
            }
        }
        return res[hole[0]][hole[1]].equals("z") ? "impossible" : res[hole[0]][hole[1]];
    }
}
