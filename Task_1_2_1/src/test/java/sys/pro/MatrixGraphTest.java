package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraphTest {
    @Test
    public void BuildFromFileTest() {
        MatrixGraph<Integer> matrix = new MatrixGraph<>();
        matrix.buildFromFile("build/resources/test/tests/1.in");
        ArrayList<Integer> result = new ArrayList<>(List.of(2));
        Assert.assertEquals(matrix.getNeighbours(1), result);
    }
}
