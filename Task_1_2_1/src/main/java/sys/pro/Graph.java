package sys.pro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Interface for graphs.
 */
public interface Graph {
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
     */
    void removeNode(Integer node);

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
     */
    void removeEdge(Integer from, Integer to);

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
    default void buildFromFile(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader((new FileReader(fileName)));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] result = line.split(" ");
                if (result.length != 2) {
                    Utils.exit("Incorrect format of file!");
                }
                try {
                    int from = Integer.parseInt(result[0]);
                    int to = Integer.parseInt(result[1]);
                    addNode(from);
                    addNode(to);
                    addEdge(from, to);
                } catch (Exception e) {
                    Utils.exit("Incorrect type of node!");
                }
            }
            reader.close();
        } catch (IOException e) {
            Utils.exit("Incorrect file " + fileName);
        }

    }
}
