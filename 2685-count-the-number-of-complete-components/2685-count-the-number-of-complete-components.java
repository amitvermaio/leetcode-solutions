class UnionFind {
    int[] rank;
    int[] parent;
    public UnionFind(int n) {
        rank = new int[n];
        parent = new int[n];

        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }

    int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu == pv) {
            return false;
        }

        if (rank[pu] > rank[pv]) {
            rank[pu]++;
            parent[pv] = pu;
        } else {
            rank[pv]++;
            parent[pu] = pv;
        }

        return true;
    }
}

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            uf.union(u, v);
        }


        HashMap<Integer, HashSet<Integer>> mp = new HashMap<>();
        for (int i=0; i<n; i++) {
            int parent = uf.find(i);
            HashSet<Integer> temp = mp.getOrDefault(parent, new HashSet<>());
            temp.add(i);
            mp.put(parent, temp);
        }

        int ans = 0;
        for (int par : mp.keySet()) {
            HashSet<Integer> temp = mp.get(par);
            if (areConnected(par, temp, adj)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean areConnected(int node, HashSet<Integer> set, ArrayList<ArrayList<Integer>> adj) {
        for (int key : set) {
            if (adj.get(key).size() != set.size()-1) {
                return false;
            }
        }

        return true;
    }
}