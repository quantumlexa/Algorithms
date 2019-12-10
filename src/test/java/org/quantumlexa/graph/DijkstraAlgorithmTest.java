package org.quantumlexa.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DijkstraAlgorithmTest {

    @Test
    public void test() {
        WeightedGraph graph = new WeightedGraph(11);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 10, 0.5);
        graph.addEdge(1, 10, 3);
        graph.addEdge(1, 4, 1);
        graph.addEdge(4, 5, 1);
        graph.addEdge(5, 6, 1);
        graph.addEdge(6, 10, 1);
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph, 1);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 10), dijkstraAlgorithm.getPathTo(10));

    }

}
