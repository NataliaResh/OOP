package sys.pro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Interface for graphs.
 */
public interface Graph {
    /**
     * Returns count of nodes.
     *
     * @return count of nodes.
     */
    int getNodesCount();

    /**
     * Returns max node's number.
     *
     * @return max node's number.
     */
    int getMaxNodeNumber();

    /**
     * Adds new node to the graph.
     *
     * @param node number of the node.
     */
    void addNode(Integer node);

    /**
     * Removes the node from the graph.
     *
     * @param node node for removing.
     * @return true if node was in graph.
     */
    boolean removeNode(Integer node);

    /**
     * Adds new edge.
     *
     * @param from start node of the edge;
     * @param to   finish node of the edge.
     */
    void addEdge(Integer from, Integer to);

    /**
     * Removes the edge.
     *
     * @param from start node of the edge;
     * @param to   finish node of the edge.
     * @return true if edge was in graph.
     */
    boolean removeEdge(Integer from, Integer to);

    /**
     * Return array with neighbours of the node.
     *
     * @param node current node.
     * @return array with neighbours of the node.
     */
    Integer[] getNeighbours(Integer node);

    /**
     * Checks if there is the node in the graph.
     *
     * @param node current node.
     * @return true if there is the node in the graph.
     */
    boolean isConsistNode(Integer node);

    /**
     * Builds the graph from the file;
     * Format of file:
     * form to
     * where every line set edge from the node {@code from} to the node {@code to}.
     *
     * @param fileName name of file.
     */
    default void buildFromFile(String fileName) throws IncorrectFormatException, IOException {
        BufferedReader reader;
        reader = new BufferedReader((new FileReader(fileName)));
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] result = line.split(" ");
            if (result.length != 2) {
                throw new IncorrectFormatException(fileName, line);
            }
            try {
                int from = Integer.parseInt(result[0]);
                int to = Integer.parseInt(result[1]);
                addEdge(from, to);
            } catch (NumberFormatException e) {
                throw new IncorrectFormatException(fileName, line);
            }
        }
        reader.close();
    }

    /**
     * Returns true if graphs are equal.
     *
     * @param graph the other graph.
     * @return true if graphs are equal.
     */
    default boolean isEqual(Graph graph) {
        if (getNodesCount() != graph.getNodesCount()) {
            return false;
        }
        for (int i = 0; i < getMaxNodeNumber(); i++) {
            if (isConsistNode(i) != graph.isConsistNode(i)) {
                return false;
            }
            if (isConsistNode(i) && graph.isConsistNode(i)) {
                if (!(Arrays.equals(getNeighbours(i), graph.getNeighbours(i)))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns graph as string.
     *
     * @param graph current graph.
     * @return string of graph.
     */
    static String toStringImpl(Graph graph) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= graph.getMaxNodeNumber(); i++) {
            if (graph.isConsistNode(i)) {
                ans.append(i).append(": ");
                String neighbours = Arrays.toString(graph.getNeighbours(i));
                ans.append(neighbours, 1, neighbours.length() - 1).append("\n");
            }
        }
        return ans.toString();
    }
}
