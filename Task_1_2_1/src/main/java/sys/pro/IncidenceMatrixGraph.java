package sys.pro;

import java.util.ArrayList;

/**
 * Class for managing graph with incidence matrix.
 */
public class IncidenceMatrixGraph implements Graph {
    static final int MAX_NODES_COUNT = 1024;
    private int nodesCapacity = 16;
    private int edgesCapacity = 16;
    private int edgesCount = 0;
    private int[][] graph = new int[nodesCapacity][edgesCapacity];
    private boolean[] consistedNodes = new boolean[nodesCapacity];

    /**
     * Constructor of graph with incidence matrix.
     */
    public IncidenceMatrixGraph() {
    }

    /**
     * Constructor of graph with incidence matrix from file.
     */
    public IncidenceMatrixGraph(String fileName) {
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
            resizeGraphNodes();
        }
        consistedNodes[node] = true;
    }

    private int[][] copyGraph() {
        int[][] newGraph = new int[nodesCapacity][edgesCapacity];
        for (int i = 0; i < nodesCapacity; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, graph[i].length);
        }
        return newGraph;
    }

    private void resizeGraphNodes() {
        if (graph.length > nodesCapacity) {
            Utils.exit("New size is less then current graph's size!");
        }
        graph = copyGraph();
        boolean[] newConsistedNodes = new boolean[nodesCapacity];
        System.arraycopy(consistedNodes, 0, newConsistedNodes, 0, consistedNodes.length);
        consistedNodes = newConsistedNodes;
    }

    private void resizeGraphEdges() {
        if (graph[0].length > edgesCapacity) {
            Utils.exit("New size is less then current graph's size!");
        }
        graph = copyGraph();
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
        for (int i = 0; i < edgesCount; i++) {
            if (graph[node][i] != 0) {
                for (int j = 0; j < nodesCapacity; j++) {
                    graph[j][i] = 0;
                }
            }
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
        if (edgesCount >= edgesCapacity) {
            edgesCapacity *= 2;
            resizeGraphEdges();
        }
        graph[from][edgesCount] = 1;
        graph[to][edgesCount] = -1;
        edgesCount++;
    }

    @Override
    public void removeEdge(Integer from, Integer to) {
        checkNode(from);
        checkNode(to);
        edgesCount--;
        int edge = -1;
        for (int j = 0; j < edgesCount; j++) {
            if (graph[from][edgesCount] == 1 && graph[to][edgesCount] == -1) {
                edge = j;
                break;
            }
        }
        if (edge != -1) {
            graph[from][edgesCount] = 0;
            graph[to][edgesCount] = 0;
        } else {
            Utils.exit("Edge is not found!");
        }
    }

    @Override
    public Integer[] getNeighbours(Integer node) {
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
}
