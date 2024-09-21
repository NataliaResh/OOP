package sys.pro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ListGraph implements Graph {
    private HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

    public ListGraph() {
    }

    public ListGraph(String fileName) {
        buildFromFile(fileName);
    }

    @Override
    public void addNode(Integer node) {
        if (graph.containsKey(node)) {
            Utils.exit("There is node in graph already!");
        }
        graph.put(node, new ArrayList<>());
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
        checkNode(from);
        checkNode(to);
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
}
