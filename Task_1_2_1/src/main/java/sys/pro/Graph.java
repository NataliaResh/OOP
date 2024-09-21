package sys.pro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface Graph {
    void addNode(Integer node);

    void removeNode(Integer node);

    void addEdge(Integer from, Integer to);

    void removeEdge(Integer from, Integer to);

    Integer[] getNeighbours(Integer node);

    boolean isConsistNode(Integer node);

    /**
     * Builds graph from file;
     * Format of file:
     * form to
     * where every line set edge from node {@code from} to node {@code to}.
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
