package blackjack;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Card.
 */
public class CardTest {
    @Test
    public void changeAceTest() {
        int aceWeight = 11;
        Card card = new Card("Ace", aceWeight);
        Assert.assertEquals(card.getWeight(), aceWeight);
        card.reduceWeight();
        Assert.assertEquals(card.getWeight(), 1);
    }

    @Test
    public void changeNoAceTest() {
        int kingWeight = 10;
        Card card = new Card("King", kingWeight);
        Assert.assertEquals(card.getWeight(), kingWeight);
        card.reduceWeight();
        Assert.assertEquals(card.getWeight(), kingWeight);
    }

    @Test
    public void openCloseCardTest() {
        int kingWeight = 10;
        Card card = new Card("King", kingWeight);
        Assert.assertFalse(card.isClosed());
        card.close();
        Assert.assertTrue(card.isClosed());
        card.open();
        Assert.assertFalse(card.isClosed());
    }

    @Test
    public void toStringTest() {
        int kingWeight = 10;
        Card card = new Card("King", kingWeight);
        Assert.assertEquals(card.toString(), "King (10)");
        card.close();
        Assert.assertEquals(card.toString(), "<закрытая карта>");
    }
}