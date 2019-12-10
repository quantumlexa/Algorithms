package org.quantumlexa.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class WeightedGraph {

    private Set<Edge>[] graph;
    private final int vertexes;

    public WeightedGraph(int vertexes) {
        this.vertexes = vertexes;
        this.graph = new Set[vertexes];
        for (int i = 0; i < vertexes; i++) {
            this.graph[i] = new HashSet<>();
        }
    }

    public Collection<Edge> getAdj(int w) {
        checkBoundaries(w);
        return graph[w];
    }

    public void addEdge(int from, int to, double weight) {
        checkBoundaries(from);
        checkBoundaries(to);
        this.graph[from].add(new Edge(from, to, weight));
    }

    private void checkBoundaries(int w) {
        if (w < 0 || w >= vertexes) {
            throw new IllegalArgumentException("the following vertex is out of boundaries: " + w);
        }

    }

    public int getNumberOfVertexes() {
        return vertexes;
    }

    static class Edge {
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        int from;
        int to;
        double weight;
    }

}
