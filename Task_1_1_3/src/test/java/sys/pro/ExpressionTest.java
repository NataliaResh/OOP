package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Expression.
 */
public class ExpressionTest {
    @Test
    public void toStringTest() {
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x")));
        Assert.assertEquals(e.toString(), "(3+(2*x))");
    }

    @Test
    public void toStringNumberTest() {
        Expression e = new Number(42);
        Assert.assertEquals(e.toString(), "42");
    }

    @Test
    public void toStringVariableTest() {
        Expression e = new Variable("y_x");
        Assert.assertEquals(e.toString(), "y_x");
    }

    @Test
    public void toStringDivisionTest() {
        Expression e = new Div(new Add(new Number(1), new Number(1)), new Number(3));
        Assert.assertEquals(e.toString(), "((1+1)/3)");
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
    public void simplifyNumberTest() {
        Expression e = new Number(42);
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "42");
    }

    @Test
    public void simplifyVariableTest() {
        Expression e = new Variable("y_x");
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "y_x");
    }

    @Test
    public void simplifyDivisionTest() {
        Expression e = new Div(new Sub(new Number(1), new Number(1)), new Number(3));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "0");
    }

    @Test
    public void simplifyMultiplicationTest() {
        Expression e = new Mul(new Sub(new Number(2), new Number(1)), new Number(3));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "3");
    }

    @Test
    public void simplifySubTest() {
        Expression e = new Sub(new Sub(new Add(new Number(1), new Number(1)), new Variable("x")),
                new Sub(new Number(2), new Variable("x")));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "0");
    }
}