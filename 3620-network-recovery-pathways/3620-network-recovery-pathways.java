class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        ArrayList<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];
        int maxEdge = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxEdge = Math.max(maxEdge, e[2]);
        }

        int[] topo = new int[n];
        Queue<Integer> q = new LinkedList<>();

        int[] indeg = indegree.clone();

        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) q.offer(i);
        }

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] e : graph[u]) {
                if (--indeg[e[0]] == 0) {
                    q.offer(e[0]);
                }
            }
        }

        int lo = 0, hi = maxEdge;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid, graph, topo, online, k, n)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(
            int minEdge, ArrayList<int[]>[] graph, int[] topo, 
            boolean[] online, long k, int n
        ) {

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF) continue;

            // Intermediate offline nodes allowed nahi hain
            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (int[] e : graph[u]) {
                int v = e[0];
                int cost = e[1];

                if (cost < minEdge) continue;
                if (v != n - 1 && !online[v]) continue;

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}