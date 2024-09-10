package blackjack;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Pack.
 */
public class PackTest {
    @Test
    public void getCardTest() {
        Pack pack = new Pack();
        Card card = pack.getCard();
        Assert.assertNotNull(card);
    }
}
