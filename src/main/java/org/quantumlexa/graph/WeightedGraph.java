package org.quantumlexa.graph;

import java.util.*;

public class WeightedGraph {

    private List<Edge>[] graph;
    private final int vertexes;

    public WeightedGraph(int vertexes) {
        this.vertexes = vertexes;
        this.graph = new List[vertexes];
        for (int i = 0; i < vertexes; i++) {
            this.graph[i] = new ArrayList<>();
        }
    }

    public Collection<Edge> getAdj(int w) {
        checkBoundaries(w);
        return graph[w];
    }


    public void addEdge(Edge edge) {
        checkBoundaries(edge.from);
        checkBoundaries(edge.to);
        this.graph[edge.from].add(edge);
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
