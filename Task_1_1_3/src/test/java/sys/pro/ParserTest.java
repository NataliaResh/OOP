package sys.pro;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Parser.
 */
public class ParserTest {
    private void parsing(String str) throws IncorrectExpressionException {
        Expression ex = Parser.parse(str);
        Assert.assertNotNull(ex);
        Assert.assertEquals(str, ex.toString());
    }

    private void correctTest(String str) {
        try {
            parsing(str);
        } catch (IncorrectExpressionException e) {
            Assert.fail();
        }
    }

    @Test
    public void correctParseTest1() {
        correctTest("(0+((0*x)+(2*1)))");
    }

    @Test
    public void correctParseTest2() {
        correctTest("(2*((4-(1+4))/(5+(2*3))))");
    }

    @Test
    public void correctParseNumberTest() {
        correctTest("1024");
    }

    @Test
    public void correctParseVariableTest() {
        correctTest("x1");
    }

    @Test
    public void incorrectParseTest() {
        Assert.assertThrows(IncorrectExpressionException.class, () -> parsing("(2*((4-(1+4))/(5+(2*3)))"));
    }
}
