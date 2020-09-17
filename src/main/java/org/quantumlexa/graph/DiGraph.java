package org.quantumlexa.graph;

import java.util.*;

public class DiGraph {
    private List<Integer>[] graph;
    private final int V;

    public DiGraph(int v) {
        this.V = v;
        this.graph = new List[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public void addAdj(int v, int w) {
        this.graph[v].add(w);
        this.graph[w].add(v);
    }

    public Collection<Integer> getAdj(int v) {
        return graph[v];
    }

}
