package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing AdjacencyMatrixGraph.
 */
public class AdjacencyMatrixGraphTest {
    @Test
    public void buildFromFileTest() {
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph("build/resources/test/tests/1.in");
        Integer[] result = {2, 6};
        Assert.assertEquals(matrix.getNeighbours(3), result);
    }

    @Test
    public void topSortTest() {
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph("build/resources/test/tests/1.in");
        Integer[] topSortMatrix = TopologicalSort.topSort(matrix);
        Integer[] ans = {5, 4, 3, 6, 2, 1};
        Assert.assertEquals(topSortMatrix, ans);
    }
}
