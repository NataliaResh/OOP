package sys.pro;

import java.util.ArrayList;
import java.util.HashMap;

public class MatrixGraph implements Graph {
    static final int MAX_NODES_COUNT = 1024;
    private int size = 16;
    private boolean[][] graph = new boolean[size][size];
    private boolean[] consistedNodes = new boolean[size];

    //private final HashMap<T, Integer> nodeToInt = new HashMap<>();
    //private final ArrayList<T> intToNode = new ArrayList<>(size);
    @Override
    public void addNode(int node) {
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
    public void removeNode(int node) {
        
        if (nodeToInt.containsKey(node)) {
            for (int i = nodeToInt.get(node) + 1; i < n; i++) {
                T currentNode = intToNode.get(i);
                intToNode.set(i - 1, currentNode);
                nodeToInt.put(currentNode, i - 1);
            }
            intToNode.remove(n - 1);
            nodeToInt.remove(node);
        }
    }

    private void setEdge(T from, T to, boolean value) {
        if (!(nodeToInt.containsKey(from) && nodeToInt.containsKey(to))) {
            Utils.exit("Node is not in graph!");
        }
        int fromInt = nodeToInt.get(from);
        int toInt = nodeToInt.get(to);
        graph[fromInt][toInt] = value;
    }

    @Override
    public void addEdge(T from, T to) {
        setEdge(from, to, true);
    }

    @Override
    public void removeEdge(T from, T to) {
        setEdge(from, to, false);
    }

    @Override
    public ArrayList<T> getNeighbours(T node) {
        int nodeInt = nodeToInt.get(node);
        ArrayList<T> neighbours = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph[nodeInt][i] && i != nodeInt) {
                neighbours.add(intToNode.get(i));
            }
        }
        return neighbours;
    }
}
