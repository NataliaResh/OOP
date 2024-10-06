package sys.pro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class for managing graph with adjacency list.
 */
public class ListGraph implements Graph {
    private int nodesCapacity = 16;
    private final HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

    /**
     * Constructor of graph with adjacency list.
     */
    public ListGraph() {
    }

    /**
     * Constructor of graph with adjacency list from file.
     *
     * @param fileName name of file.
     * @throws IncorrectFormatException if format of graph in the file is incorrect.
     * @throws IOException              if there are some problems with the file.
     */
    public ListGraph(String fileName) throws IncorrectFormatException, IOException {
        buildFromFile(fileName);
    }

    @Override
    public int getNodesCount() {
        return graph.size();
    }

    @Override
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
    public boolean removeNode(Integer node) {
        if (!isConsistNode(node)) {
            return false;
        }
        graph.remove(node);
        for (ArrayList<Integer> nodes : graph.values()) {
            nodes.remove(node);
        }
        return true;
    }

    @Override
    public boolean isConsistNode(Integer node) {
        return graph.containsKey(node);
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
    public boolean removeEdge(Integer from, Integer to) {
        if (!isConsistNode(from)) {
            return false;
        }
        return graph.get(from).remove(to);
    }

    @Override
    public Integer[] getNeighbours(Integer node) {
        if (!isConsistNode(node)) {
            return null;
        }
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
