package sys.pro;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface Graph {
    void addNode(int node);

    void removeNode(T node);

    void addEdge(T from, T to);

    void removeEdge(T from, T to);

    ArrayList<T> getNeighbours(T node);

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
                    Class<T> from = (Class<T>)result[0];
                    T to = (Class<T>)result[1];
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
