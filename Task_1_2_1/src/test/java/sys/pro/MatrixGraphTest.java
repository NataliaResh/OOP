package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class MatrixGraphTest {
    @Test
    public void BuildFromFileTest() {
        MatrixGraph matrix = new MatrixGraph("build/resources/test/tests/1.in");
        Integer[] result = {2};
        Assert.assertEquals(matrix.getNeighbours(1), result);
    }
}
