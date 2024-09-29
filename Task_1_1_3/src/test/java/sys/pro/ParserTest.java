package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Parser.
 */
public class ParserTest {
    private void parsing(String str, String ans) throws IncorrectExpressionException {
        Expression ex = Parser.parse(str);
        Assert.assertNotNull(ex);
        Assert.assertEquals(ans, ex.toString());
    }

    private void correctTest(String str, String ans) {
        try {
            parsing(str, ans);
        } catch (IncorrectExpressionException e) {
            Assert.fail();
        }
    }

    @Test
    public void emptyParseTest() {
        try {
            Expression ex = Parser.parse("");
            Assert.assertNull(ex);
        } catch (IncorrectExpressionException e) {
            Assert.fail();
        }

    }

    @Test
    public void correctParseTest1() {
        String str = "(0+((0*x)+(2*1)))";
        correctTest(str, str);
    }

    @Test
    public void correctParseTest2() {
        String str = "(2*((4-(1+4))/(5+(2*3))))";
        correctTest(str, str);
    }

    @Test
    public void correctParseTest3() {
        String str = "1+2+3+4/2";
        String ans = "(1+(2+(3+(4/2))))";
        correctTest(str, ans);
    }

    @Test
    public void correctParseTest4() {
        String str = "1*3-(1+4/2)";
        String ans = "((1*3)-(1+(4/2)))";
        correctTest(str, ans);
    }

    @Test
    public void correctParseNumberTest() {
        String str = "1024";
        correctTest(str, str);
    }

    @Test
    public void correctParseVariableTest() {
        String str = "x1";
        correctTest(str, str);
    }

    @Test
    public void incorrectParseTest() {
        String str = "(2*((4-(1+4))/(5+(2*3)))";
        Assert.assertThrows(IncorrectExpressionException.class,
                () -> parsing(str, str));
    }
}
