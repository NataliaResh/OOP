package sys.pro;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Class for managing graph with adjacency matrix.
 */
public class AdjacencyMatrixGraph implements Graph {
    private int nodesCapacity = 16;
    private int nodesCount = 0;
    private int maxNode = -1;
    private boolean[][] graph = new boolean[nodesCapacity][nodesCapacity];
    private boolean[] consistedNodes = new boolean[nodesCapacity];

    /**
     * Constructor of graph with adjacency matrix.
     */
    public AdjacencyMatrixGraph() {
    }

    /**
     * Constructor of graph with adjacency matrix from file.
     *
     * @param fileName name of file.
     * @throws IncorrectFormatException if format of graph in the file is incorrect.
     * @throws IOException              if there are some problems with the file.
     */
    public AdjacencyMatrixGraph(String fileName) throws IncorrectFormatException, IOException {
        buildFromFile(fileName);
    }

    @Override
    public int getNodesCount() {
        return nodesCount;
    }

    @Override
    public int getMaxNodeNumber() {
        return maxNode;
    }

    @Override
    public void addNode(Integer node) {
        while (node >= nodesCapacity) {
            resizeGraph();
        }
        maxNode = Math.max(node, maxNode);
        consistedNodes[node] = true;
        nodesCount++;
    }

    private void resizeGraph() {
        nodesCapacity *= 2;
        boolean[][] newGraph = new boolean[nodesCapacity][nodesCapacity];
        for (int i = 0; i < graph.length; i++) {
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

    @Override
    public boolean removeNode(Integer node) {
        if (!isConsistNode(node)) {
            return false;
        }
        consistedNodes[node] = false;
        for (int i = 0; i < nodesCapacity; i++) {
            graph[i][node] = false;
            graph[node][i] = false;
        }
        nodesCount--;
        return true;
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
    public boolean removeEdge(Integer from, Integer to) {
        if (!isConsistNode(from)) {
            return false;
        }
        if (!isConsistNode(to)) {
            return false;
        }
        if (!graph[from][to]) {
            return false;
        }
        graph[from][to] = false;
        return true;
    }

    @Override
    public Integer[] getNeighbours(Integer node) {
        if (!isConsistNode(node)) {
            return null;
        }
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < nodesCapacity; i++) {
            if (graph[node][i]) {
                neighbours.add(i);
            }
        }
        return neighbours.toArray(new Integer[0]);
    }

    @Override
    public String toString() {
        return Graph.toStringImpl(this);
    }
}
