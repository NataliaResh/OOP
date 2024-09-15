package blackjack;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Dealer.
 */
public class DealerTest {
    @Test
    public void initPlayerTest() {
        Pack pack = new Pack();
        Dealer dealer = new Dealer();
        dealer.initPlayer(pack);
        Card card = dealer.getClosedCard();
        Assert.assertTrue(card.isClosed());
        dealer.openClosedCard();
        Assert.assertFalse(card.isClosed());
    }
}