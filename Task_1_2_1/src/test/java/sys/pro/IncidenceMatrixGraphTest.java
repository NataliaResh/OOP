package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class IncidenceMatrixGraphTest {
    @Test
    public void BuildFromFileTest() {
        IncidenceMatrixGraph matrix = new IncidenceMatrixGraph("build/resources/test/tests/1.in");
        Integer[] result = {2, 6};
        Assert.assertEquals(matrix.getNeighbours(3), result);
    }
}
