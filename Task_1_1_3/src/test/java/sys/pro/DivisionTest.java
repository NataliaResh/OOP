package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Division operation.
 */
public class DivisionTest {
    @Test
    public void toStringDivisionTest() {
        Expression e = new Div(new Add(new Number(1), new Number(1)), new Number(3));
        Assert.assertEquals(e.toString(), "((1+1)/3)");
    }

    @Test
    public void derivativeTest() {
        Expression e = new Div(new Variable("x"), new Add(new Number(2),
                new Variable("x")));
        Expression de = e.derivative("x");
        Assert.assertEquals(de.toString(), "(((1*(2+x))-(x*(0+1)))/((2+x)*(2+x)))");
        Assert.assertEquals(e.toString(), "(x/(2+x))");
    }

    @Test
    public void evalTest() {
        Expression e = new Div(new Add(new Number(2), new Variable("x")),
                new Variable("x"));
        int result = 0;
        try {
            result = e.eval("x = 2; y = 13");
        } catch (NotEnoughSignificationsExpression ex) {
            Assert.fail();
        }
        Assert.assertEquals(result, 2);
    }

    @Test
    public void simplifyTest1() {
        Expression e = new Div(new Sub(new Number(1), new Number(1)), new Number(3));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "0");
    }

    @Test
    public void simplifyTest2() {
        Expression e = new Div(new Div(new Variable("x"), new Number(1)), new Number(2));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "(x/2)");
    }

    @Test
    public void simplifyTest3() {
        Expression e = new Div(new Div(new Number(0), new Variable("x")), new Number(2));
        Expression simplified = e.simplify();
        Assert.assertEquals(simplified.toString(), "0");
    }

    @Test
    public void copyTest() {
        Expression e = new Div(new Add(new Number(2), new Variable("x")),
                new Variable("x"));
        Expression copeE = e.copy();
        Assert.assertEquals(e.toString(), copeE.toString());
    }
}
