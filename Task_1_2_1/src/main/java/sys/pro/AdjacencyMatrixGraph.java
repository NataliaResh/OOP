package sys.pro;

import java.util.ArrayList;

/**
 * Class for managing graph with adjacency matrix.
 */
public class AdjacencyMatrixGraph implements Graph {
    static final int MAX_NODES_COUNT = 1024;
    private int size = 16;
    private boolean[][] graph = new boolean[size][size];
    private boolean[] consistedNodes = new boolean[size];

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

    @Override
    public void addNode(Integer node) {
        if (node >= MAX_NODES_COUNT) {
            Utils.exit("Can't add node with number more then " + MAX_NODES_COUNT);
        }
        if (node >= size) {
            size *= 2;
            resizeGraph();
        }
        consistedNodes[node] = true;
    }

    private void resizeGraph() {
        if (graph.length > size) {
            Utils.exit("New size is less then current graph's size!");
        }
        boolean[][] newGraph = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, graph.length);
        }
        graph = newGraph;
        boolean[] newConsistedNodes = new boolean[size];
        System.arraycopy(consistedNodes, 0, newConsistedNodes, 0, consistedNodes.length);
        consistedNodes = newConsistedNodes;
    }

    @Override
    public boolean isConsistNode(Integer node) {
        return node >= 0 && node < size && consistedNodes[node];
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
        for (int i = 0; i < size; i++) {
            graph[i][node] = false;
            graph[node][i] = false;
        }
    }

    private void setEdge(Integer from, Integer to, boolean value) {
        checkNode(from);
        checkNode(to);
        graph[from][to] = value;
    }

    @Override
    public void addEdge(Integer from, Integer to) {
        setEdge(from, to, true);
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        setEdge(from, to, false);
    }

    @Override
    public Integer[] getNeighbours(Integer node) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (graph[node][i]) {
                neighbours.add(i);
            }
        }
        return neighbours.toArray(new Integer[0]);
    }
}
