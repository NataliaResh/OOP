package sys.pro;

import java.util.HashMap;
import java.util.Stack;

public class TopologicalSort {
    private static void dfs(int node, AdjacencyMatrixGraph graph, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbour: graph.getNeighbours(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited, stack);
            }
        }
        stack.push(node);
    }

    public static AdjacencyMatrixGraph topSort(AdjacencyMatrixGraph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.getNodesCapacity()];
        for (int i = 0; i < graph.getNodesCapacity(); i++) {
            if (graph.isConsistNode(i) && !visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stack.size(); i++) {
            map.put(stack.get(i), i);
        }
        AdjacencyMatrixGraph newGraph = new AdjacencyMatrixGraph();
        for (int node: stack) {
            for (int neighbour: graph.getNeighbours(node)) {
                newGraph.addEdge(map.get(node), map.get(neighbour));
            }
        }
        return newGraph;
    }
}
