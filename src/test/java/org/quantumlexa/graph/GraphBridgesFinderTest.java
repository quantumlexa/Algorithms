package org.quantumlexa.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphBridgesFinderTest {

    @Test
    public void findBridges() {
        DiGraph graph = new DiGraph(7);
        graph.addAdj(0, 1);
        graph.addAdj(0, 2);
        graph.addAdj(1, 3);
        graph.addAdj(2, 3);
        graph.addAdj(3, 4);
        graph.addAdj(3, 5);
        graph.addAdj(4, 6);
        graph.addAdj(5, 6);
        GraphBridgesFinder finder = new GraphBridgesFinder();
        List<List<Integer>> bridges = finder.findBridges(graph);
        for (List<Integer> list : bridges) {
            System.out.println(list);
        }

    }
}