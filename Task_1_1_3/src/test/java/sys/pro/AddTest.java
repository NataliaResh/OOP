package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Add operation.
 */
public class AddTest {
    @Test
    public void toStringTest() {
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        Assert.assertEquals(e.toString(), "(3+(2*x))");
    }

    @Test
    public void derivativeTest1() {
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        Expression de = e.derivative("x");
        Assert.assertEquals(de.toString(), "(0+((0*x)+(2*1)))");
        Assert.assertEquals(e.toString(), "(3+(2*x))");
    }

    @Test
    public void derivativeTest2() {
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        Expression de = e.derivative("y");
        Assert.assertEquals(de.toString(), "(0+((0*x)+(2*0)))");
        Assert.assertEquals(e.toString(), "(3+(2*x))");
    }

    @Test
    public void evalTest() {
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        int result = 0;
        try {
            result = e.eval("x = 10; y = 13");
        } catch (NotEnoughSignificationsExpression ex) {
            Assert.fail();
        }
        Assert.assertEquals(result, 23);
    }

    @Test
    public void simplifyTest1() {
        Expression e = new Add(new Add(new Variable("x"), new Add(new Number(1), new Number(2))), new Number(0));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "(x+3)");
    }

    @Test
    public void simplifyTest2() {
        Expression e = new Add(new Number(0), new Add(new Variable("x"), new Add(new Number(1), new Number(2))));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "(x+3)");
    }
}
