package blackjack;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;

/**
 * Class for testing Pack.
 */
public class PackTest {
    @Test
    public void getCardTest() {
        Pack pack = new Pack();
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            Card card = pack.getCard();
            Assert.assertNotNull(card);
            for (Card value : cards) {
                Assert.assertNotEquals(card, value);
            }
            cards.add(card);
        }
        Card card = pack.getCard();
        Assert.assertNull(card);
    }
}
