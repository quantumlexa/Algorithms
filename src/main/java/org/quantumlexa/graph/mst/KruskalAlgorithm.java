package org.quantumlexa.graph.mst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalAlgorithm {

    public List<int[]>[] findMst(List<int[]>[] graph) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        for (List<int[]> lists : graph) {
            for (int[] e : lists) {
                pq.add(e);
            }
        }
        UF uf = new UF(graph.length);
        List<int[]>[] mst = new List[graph.length];
        for (int i = 0; i < mst.length; i++) {
            mst[i] = new LinkedList<>();
        }
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int from = edge[0];
            int to = edge[1];
            if (uf.connected(from, to)) continue;
            mst[from].add(edge);
            uf.connect(from, to);
        }
        return mst;
    }

    static class UF {
        int[] id;

        public UF(int n) {
            this.id = new int[n];
            for (int i = 0; i < n; i++) id[i] = i;
        }

        private int root(int a) {
            while (a != id[a]) {
                id[a] = id[id[a]];
                a = id[a];
            }
            return a;
        }

        public void connect(int a, int b) {
            int rootA = root(a);
            int rootB = root(b);
            if (rootA == rootB) return;
            id[rootA] = rootB;
        }

        public boolean connected(int a, int b) {
            return root(a) == root(b);
        }


    }


}
