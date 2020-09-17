package org.quantumlexa.graph;

import java.util.*;

public class BellmanFordAlgorithm {

    private List<int[]>[] graph;
    private int[] pathTo;
    private boolean hasCycle;
    int source;

    public BellmanFordAlgorithm(List<int[]>[] graph, int source) {
        this.graph = graph;
        this.source = source;
        this.pathTo = new int[graph.length];
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        for (int times = 0; times < graph.length; times++) {
            for (int i = 0; i < graph.length; i++) {
                for (int[] edge : graph[i]) {
                    if (dist[edge[0]] < Integer.MAX_VALUE && dist[edge[1]] > dist[edge[0]] + edge[2]) {
                        dist[edge[1]] = dist[edge[0]] + edge[2];
                        pathTo[edge[1]] = edge[0];
                    }
                }
            }
        }
        for (int i = 0; i < graph.length; i++) {
            for (int[] edges : graph[i]) {
                int from = edges[0];
                int to = edges[1];
                if (dist[to] < Integer.MAX_VALUE && dist[to] > dist[from] + edges[2]) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public List<Integer> getPathTo(int target) {
        int n = target;
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(target);
        while (pathTo[n] != source) {
            list.addFirst(pathTo[n]);
            n = pathTo[n];
        }
        list.addFirst(pathTo[n]);
        return list;
    }

}
