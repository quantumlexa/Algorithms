package org.quantumlexa.graph;

import java.util.*;

public class DijkstraAlgorithm {
    private int[] path;
    private double[] dist;
    private int source;
    private WeightedGraph graph;


    public DijkstraAlgorithm(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        calculateShortestPath(graph, source);
    }


    private void calculateShortestPath(WeightedGraph graph, int source) {
        this.path = new int[graph.getNumberOfVertexes()];
        Arrays.fill(path, -1);
        path[source] = source;

        this.dist = new double[graph.getNumberOfVertexes()];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[source] = 0;
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.weight));
        queue.add(new Node(source, 0));
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (WeightedGraph.Edge e : graph.getAdj(n.v)) {
                if (dist[e.from] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[e.from] + e.weight;
                    path[e.to] = e.from;
                    queue.add(new Node(e.to, dist[e.to]));
                }
            }
        }
    }

    public List<Integer> getPathTo(int target) {
        int n = target;
        LinkedList<Integer> list = new LinkedList<>();
        while (path[n] != n) {
            list.addFirst(n);
            n = path[n];
        }
        list.addFirst(n);
        return list;
    }


    private static class Node {
        public Node(int v, double weight) {
            this.v = v;
            this.weight = weight;
        }

        int v;
        double weight;
    }
}
