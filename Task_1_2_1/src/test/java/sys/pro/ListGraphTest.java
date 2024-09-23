package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class ListGraphTest {
    @Test
    public void BuildFromFileTest() {
        ListGraph matrix = new ListGraph("build/resources/test/tests/1.in");
        Integer[] result = {2, 6};
        Assert.assertEquals(matrix.getNeighbours(3), result);
    }

    @Test
    public void TopSortTest() {
        ListGraph matrix = new ListGraph("build/resources/test/tests/1.in");
        Integer[] topSortMatrix = TopologicalSort.topSort(matrix);
        Integer[] ans = {5, 4, 3, 6, 2, 1};
        Assert.assertEquals(topSortMatrix, ans);
    }
}