package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Variable.
 */
public class VariableTest {
    @Test
    public void toStringVariableTest() {
        Expression e = new Variable("y_x");
        Assert.assertEquals(e.toString(), "y_x");
    }

    @Test
    public void derivativeTest1() {
        Expression e = new Variable("x");
        Expression de = e.derivative("x");
        Assert.assertEquals(de.toString(), "1");
        Assert.assertEquals(e.toString(), "x");
    }

    @Test
    public void derivativeTest2() {
        Expression e = new Variable("x");
        Expression de = e.derivative("y");
        Assert.assertEquals(de.toString(), "0");
        Assert.assertEquals(e.toString(), "x");
    }

    @Test
    public void evalVariableTest() {
        Expression e = new Variable("var");
        int result = 0;
        try {
            result = e.eval("var = 42");
        } catch (NotEnoughSignificationsExpression ex) {
            Assert.fail();
        }
        Assert.assertEquals(result, 42);
    }

    @Test
    public void simplifyVariableTest() {
        Expression e = new Variable("y_x");
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "y_x");
    }
}
