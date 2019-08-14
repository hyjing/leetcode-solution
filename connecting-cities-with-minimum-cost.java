// There are N cities numbered from 1 to N.
// You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
//  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

// Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
// union find with minimum spanning tree!
class Solution {
    int n;
    int[] parent;

    private int find(int x) {
        if (x == parent[x]) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parent[px] = py;
            n--;
        }
    }

    public int minimumCost(int N, int[][] connections) {
        n = N;
        parent = new int[N + 1];
        
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        int res = 0;
        for (int[] edge : connections) {
            int x = edge[0];
            int y = edge[1];
            if (find(x) != find(y)) {
                res += edge[2];
                union(x, y);
            }
        }
        
        return n == 1 ? res : -1;
    }
}
