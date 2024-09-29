package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Number.
 */
public class NumberTest {
    @Test
    public void toStringNumberTest() {
        Expression e = new Number(42);
        Assert.assertEquals(e.toString(), "42");
    }

    @Test
    public void derivativeTest() {
        Expression e = new Number(3);
        Expression de = e.derivative("x");
        Assert.assertEquals(de.toString(), "0");
        Assert.assertEquals(e.toString(), "3");
    }
    @Test
    public void evalNumberTest1() {
        Expression e = new Number(42);
        int result = 0;
        try {
            result = e.eval("x = 10");
        } catch (NotEnoughSignificationsExpression ex) {
            Assert.fail();
        }
        Assert.assertEquals(result, 42);
    }

    @Test
    public void evalNumberTest2() {
        Expression e = new Number(42);
        int result = 0;
        try {
            result = e.eval("");
        } catch (NotEnoughSignificationsExpression ex) {
            Assert.fail();
        }
        Assert.assertEquals(result, 42);
    }

    @Test
    public void simplifyNumberTest() {
        Expression e = new Number(42);
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "42");
    }
}
