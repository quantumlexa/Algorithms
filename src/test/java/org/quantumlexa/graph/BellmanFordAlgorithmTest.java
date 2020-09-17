package org.quantumlexa.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BellmanFordAlgorithmTest {
    @Test
    public void testNoCycles() {
        List<int[]>[] graph = new List[11];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        addEdge(1, 2, 1, graph);
        addEdge(2, 3, 1, graph);
        addEdge(3, 10, 1, graph);
        addEdge(1, 10, 4, graph);
        addEdge(1, 4, 1, graph);
        addEdge(4, 5, 1, graph);
        addEdge(5, 6, 1, graph);
        addEdge(6, 10, 1, graph);
        BellmanFordAlgorithm bellmanFordAlgorithm = new BellmanFordAlgorithm(graph, 1);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 10), bellmanFordAlgorithm.getPathTo(10));
        Assert.assertFalse(bellmanFordAlgorithm.hasCycle());
    }


    private void addEdge(int from, int to, int weight, List<int[]>[] graph) {
        graph[from].add(new int[]{from, to, weight});

    }
}
