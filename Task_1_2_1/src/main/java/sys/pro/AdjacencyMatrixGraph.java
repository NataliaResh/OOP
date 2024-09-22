package sys.pro;

import java.util.ArrayList;

/**
 * Class for managing graph with adjacency matrix.
 */
public class AdjacencyMatrixGraph implements Graph {
    static final int MAX_NODES_COUNT = 1024;
    private int nodesCapacity = 16;
    private boolean[][] graph = new boolean[nodesCapacity][nodesCapacity];
    private boolean[] consistedNodes = new boolean[nodesCapacity];

    /**
     * Constructor of graph with adjacency matrix.
     */
    public AdjacencyMatrixGraph() {
    }

    /**
     * Constructor of graph with adjacency matrix from file.
     */
    public AdjacencyMatrixGraph(String fileName) {
        buildFromFile(fileName);
    }

    public int getNodesCapacity() {
        return nodesCapacity;
    }

    @Override
    public void addNode(Integer node) {
        if (node >= MAX_NODES_COUNT) {
            Utils.exit("Can't add node with number more then " + MAX_NODES_COUNT);
        }
        if (node >= nodesCapacity) {
            nodesCapacity *= 2;
            resizeGraph();
        }
        consistedNodes[node] = true;
    }

    private void resizeGraph() {
        if (graph.length > nodesCapacity) {
            Utils.exit("New size is less then current graph's size!");
        }
        boolean[][] newGraph = new boolean[nodesCapacity][nodesCapacity];
        for (int i = 0; i < nodesCapacity; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, graph.length);
        }
        graph = newGraph;
        boolean[] newConsistedNodes = new boolean[nodesCapacity];
        System.arraycopy(consistedNodes, 0, newConsistedNodes, 0, consistedNodes.length);
        consistedNodes = newConsistedNodes;
    }

    @Override
    public boolean isConsistNode(Integer node) {
        return node >= 0 && node < nodesCapacity && consistedNodes[node];
    }

    private void checkNode(Integer node) {
        if (!isConsistNode(node)) {
            Utils.exit("Node is not in graph!");
        }
    }

    @Override
    public void removeNode(Integer node) {
        checkNode(node);
        consistedNodes[node] = false;
        for (int i = 0; i < nodesCapacity; i++) {
            graph[i][node] = false;
            graph[node][i] = false;
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
        graph[from][to] = true;
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        checkNode(from);
        checkNode(to);
        graph[from][to] = false;
    }

    @Override
    public Integer[] getNeighbours(Integer node) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < nodesCapacity; i++) {
            if (graph[node][i]) {
                neighbours.add(i);
            }
        }
        return neighbours.toArray(new Integer[0]);
    }
}
