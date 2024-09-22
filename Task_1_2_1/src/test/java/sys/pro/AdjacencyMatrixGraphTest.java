package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class AdjacencyMatrixGraphTest {
    @Test
    public void BuildFromFileTest() {
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph("build/resources/test/tests/1.in");
        Integer[] result = {2, 6};
        Assert.assertEquals(matrix.getNeighbours(3), result);
    }

    @Test
    public void TopSortTest() {
        AdjacencyMatrixGraph matrix = new AdjacencyMatrixGraph("build/resources/test/tests/1.in");
        AdjacencyMatrixGraph topSortMatrix = TopologicalSort.topSort(matrix);
        AdjacencyMatrixGraph sortedMatrix = new AdjacencyMatrixGraph("build/resources/test/tests/sorted.in");
        Assert.assertTrue(topSortMatrix.isEqual(sortedMatrix));
    }
}
