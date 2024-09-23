package sys.pro;

import java.util.Stack;

/**
 * Class for managing topological sort.
 */
public class TopologicalSort {
    /**
     * Performs depth-first search.
     *
     * @param node    first node;
     * @param graph   current graph;
     * @param visited array of visited nodes;
     * @param stack   current stack.
     */
    private static void dfs(int node, Graph graph, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbour : graph.getNeighbours(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited, stack);
            }
        }
        stack.push(node);
    }

    /**
     * Implements topological sort and return right sequences of nodes.
     *
     * @param graph current graph.
     * @return right sequences of nodes.
     */
    public static Integer[] topSort(Graph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.getNodesCapacity()];
        for (int i = 0; i < graph.getNodesCapacity(); i++) {
            if (graph.isConsistNode(i) && !visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }
        int n = stack.size();
        Integer[] ans = new Integer[n];
        for (int i = 0; i < n; i++) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
