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
        for (int i = 0; i < pack.packCards.size(); i++) {
            Assert.assertNotEquals(card, pack.packCards.get(i));
        }
    }
}
