package sys.pro;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Class for managing graph with incidence matrix.
 */
public class IncidenceMatrixGraph implements Graph {
    private int nodesCapacity = 16;
    private int edgesCapacity = 16;
    private int edgesCount = 0;
    private int nodesCount = 0;
    private int maxNode = -1;
    private int[][] graph = new int[nodesCapacity][edgesCapacity];
    private boolean[] consistedNodes = new boolean[nodesCapacity];

    /**
     * Constructor of graph with incidence matrix.
     */
    public IncidenceMatrixGraph() {
    }

    /**
     * Constructor of graph with incidence matrix from file.
     *
     * @param fileName name of file.
     * @throws IncorrectFormatException if format of graph in the file is incorrect.
     * @throws IOException              if there are some problems with the file.
     */
    public IncidenceMatrixGraph(String fileName) throws IncorrectFormatException, IOException {
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
            resizeGraphNodes();
        }
        maxNode = Math.max(node, maxNode);
        consistedNodes[node] = true;
        nodesCount++;
    }

    private int[][] copyGraph() {
        int[][] newGraph = new int[nodesCapacity][edgesCapacity];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, graph[i].length);
        }
        return newGraph;
    }

    private void resizeGraphNodes() {
        nodesCapacity *= 2;
        graph = copyGraph();
        boolean[] newConsistedNodes = new boolean[nodesCapacity];
        System.arraycopy(consistedNodes, 0, newConsistedNodes, 0, consistedNodes.length);
        consistedNodes = newConsistedNodes;
    }

    private void resizeGraphEdges() {
        edgesCapacity *= 2;
        graph = copyGraph();
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
        for (int i = 0; i < edgesCount; i++) {
            if (graph[node][i] != 0) {
                for (int j = 0; j < nodesCapacity; j++) {
                    graph[j][i] = 0;
                }
            }
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
        if (edgesCount >= edgesCapacity) {
            resizeGraphEdges();
        }
        graph[from][edgesCount] = 1;
        graph[to][edgesCount] = -1;
        edgesCount++;
    }

    @Override
    public boolean removeEdge(Integer from, Integer to) {
        if (!isConsistNode(from)) {
            return false;
        }
        if (!isConsistNode(to)) {
            return false;
        }
        int edge = -1;
        for (int j = 0; j < edgesCount; j++) {
            if (graph[from][j] == 1 && graph[to][j] == -1) {
                edge = j;
                break;
            }
        }
        if (edge == -1) {
            return false;
        }
        edgesCount--;
        graph[from][edge] = 0;
        graph[to][edge] = 0;
        return true;

    }

    @Override
    public Integer[] getNeighbours(Integer node) {
        if (!isConsistNode(node)) {
            return null;
        }
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < edgesCount; i++) {
            if (graph[node][i] == 1) {
                for (int j = 0; j < nodesCapacity; j++) {
                    if (graph[j][i] == -1) {
                        neighbours.add(j);
                    }
                }
            }
        }
        return neighbours.toArray(new Integer[0]);
    }


    @Override
    public String toString() {
        return Graph.toStringImpl(this);
    }
}
