package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Multiplication operation.
 */
public class MulTest {
    @Test
    public void toStringTest() {
        Expression e = new Mul(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        Assert.assertEquals(e.toString(), "(3*(2*x))");
    }

    @Test
    public void derivativeTest1() {
        Expression e = new Mul(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        Expression de = e.derivative("x");
        Assert.assertEquals(de.toString(), "((0*(2*x))+(3*((0*x)+(2*1))))");
        Assert.assertEquals(e.toString(), "(3*(2*x))");
    }

    @Test
    public void derivativeTest2() {
        Expression e = new Mul(new Number(3), new Sub(new Number(2),
                new Variable("x")));
        Expression de = e.derivative("x");
        Assert.assertEquals(de.toString(), "((0*(2-x))+(3*(0-1)))");
        Assert.assertEquals(e.toString(), "(3*(2-x))");
    }

    @Test
    public void evalTest() {
        Expression e = new Mul(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        int result = 0;
        try {
            result = e.eval("x = 10; y = 13");
        } catch (NotEnoughSignificationsExpression ex) {
            Assert.fail();
        }
        Assert.assertEquals(result, 60);
    }


    @Test
    public void simplifyTest1() {
        Expression e = new Mul(new Sub(new Number(2), new Number(1)), new Number(3));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "3");
    }

    @Test
    public void simplifyTest2() {
        Expression e = new Mul(new Mul(new Variable("x"), new Number(1)), new Number(2));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "(x*2)");
    }

    @Test
    public void simplifyTest3() {
        Expression e = new Mul(new Mul(new Number(0), new Variable("x")), new Number(2));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "0");
    }

    @Test
    public void simplifyTest4() {
        Expression e = new Mul(new Mul(new Number(1), new Number(1)), new Mul(new Number(3), new Variable("x")));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "(3*x)");
    }
}
