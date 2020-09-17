package org.quantumlexa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphBridgesFinder {

    public List<List<Integer>> findBridges(DiGraph diGraph) {
        int[] low = new int[diGraph.V()];
        int[] pre = new int[diGraph.V()];
        Arrays.fill(low, -1);
        Arrays.fill(pre, -1);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < diGraph.V(); i++) {
            if (pre[i] == -1) {
                dfs(diGraph, i, i, pre, low, 0, result);
            }
        }

        System.out.println("pre:" + Arrays.toString(pre));
        System.out.println("low:" + Arrays.toString(low));
        return result;
    }


    private void dfs(DiGraph graph, int to, int from, int[] pre, int[] low, int id, List<List<Integer>> result) {
        pre[from] = id;
        low[from] = pre[from];
        for (int w : graph.getAdj(from)) {
            if (pre[w] == -1) {
                dfs(graph, from, w, pre, low, id + 1, result);
                low[from] = Math.min(low[from], low[w]);
                if (low[w] == pre[w]) {
                    result.add(Arrays.asList(from, w));
                }
            } else if (w != to) {
                low[from] = Math.min(low[from], pre[w]);
            }
        }
    }

}
