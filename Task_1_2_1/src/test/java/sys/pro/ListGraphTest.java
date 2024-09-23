package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing ListGraph.
 */
public class ListGraphTest {
    @Test
    public void buildFromFileTest() {
        ListGraph matrix = new ListGraph("build/resources/test/tests/1.in");
        Integer[] result = {2, 6};
        Assert.assertEquals(matrix.getNeighbours(3), result);
    }

    @Test
    public void topSortTest() {
        ListGraph matrix = new ListGraph("build/resources/test/tests/1.in");
        Integer[] topSortMatrix = TopologicalSort.topSort(matrix);
        Integer[] ans = {5, 4, 3, 6, 2, 1};
        Assert.assertEquals(topSortMatrix, ans);
    }

    @Test
    public void toStringTest() {
        ListGraph matrix = new ListGraph("build/resources/test/tests/1.in");
        String result = matrix.toString();
        String ans = "1: \n2: 1\n3: 2, 6\n4: 1\n5: 4\n6: \n";
        Assert.assertEquals(result, ans);
    }

    @Test
    public void buildTest() {
        ListGraph matrix = new ListGraph();
        matrix.addNode(4);
        matrix.addNode(2);
        matrix.addEdge(2, 1);
        matrix.addEdge(4, 1);
        matrix.addEdge(5, 4);
        matrix.addEdge(3, 2);
        matrix.addNode(6);
        matrix.addEdge(3, 6);
        ListGraph result = new ListGraph("build/resources/test/tests/1.in");
        Assert.assertTrue(matrix.isEqual(result));
    }
}