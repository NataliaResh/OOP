package sys.pro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class for managing graph with adjacency list.
 */
public class ListGraph implements Graph {
    private int nodesCapacity = 16;
    private HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

    /**
     * Constructor of graph with adjacency list.
     */
    public ListGraph() {
    }

    /**
     * Constructor of graph with adjacency list from file.
     */
    public ListGraph(String fileName) {
        buildFromFile(fileName);
    }

    public int getNodesCapacity() {
        return nodesCapacity;
    }

    @Override
    public void addNode(Integer node) {
        if (!graph.containsKey(node)) {
            graph.put(node, new ArrayList<>());
            nodesCapacity = Math.max(nodesCapacity, node + 1);
        }
    }

    @Override
    public void removeNode(Integer node) {
        checkNode(node);
        graph.remove(node);
        for (ArrayList<Integer> nodes : graph.values()) {
            nodes.remove(node);
        }
    }

    @Override
    public boolean isConsistNode(Integer node) {
        return graph.containsKey(node);
    }

    private void checkNode(Integer node) {
        if (!isConsistNode(node)) {
            Utils.exit("Node is not in graph!");
        }
    }

    @Override
    public void addEdge(Integer from, Integer to) {
        if (!isConsistNode(from)) {
            addNode(from);
        }
        if (!isConsistNode(to)) {
            addNode(to);
        }
        graph.get(from).add(to);
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        checkNode(from);
        checkNode(to);
        graph.get(from).remove(to);
    }

    @Override
    public Integer[] getNeighbours(Integer node) {
        checkNode(node);
        Integer[] neighbours = graph.get(node).toArray(new Integer[0]);
        return Arrays.copyOf(neighbours, neighbours.length);
    }

    /**
     * Returns graph as string.
     *
     * @return string of graph.
     */
    @Override
    public String toString() {
        return Graph.toStringImpl(this);
    }
}
