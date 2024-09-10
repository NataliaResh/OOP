package blackjack;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Card.
 */
public class CardTest {
    @Test
    public void changeAceTest() {
        int ACE_WEIGHT = 11;
        Card card = new Card("Ace", ACE_WEIGHT);
        Assert.assertEquals(card.getWeight(), ACE_WEIGHT);
        card.reduceWeight();
        Assert.assertEquals(card.getWeight(), 1);
    }

    @Test
    public void changeNoAceTest() {
        int KING_WEIGHT = 10;
        Card card = new Card("King", KING_WEIGHT);
        Assert.assertEquals(card.getWeight(), KING_WEIGHT);
        card.reduceWeight();
        Assert.assertEquals(card.getWeight(), KING_WEIGHT);
    }

    @Test
    public void openCloseCardTest() {
        int KING_WEIGHT = 10;
        Card card = new Card("King", KING_WEIGHT);
        Assert.assertFalse(card.isClosed());
        card.close();
        Assert.assertTrue(card.isClosed());
        card.open();
        Assert.assertFalse(card.isClosed());
    }

    @Test
    public void toStringTest() {
        int KING_WEIGHT = 10;
        Card card = new Card("King", KING_WEIGHT);
        Assert.assertEquals(card.toString(), "King (10)");
        card.close();
        Assert.assertEquals(card.toString(), "<закрытая карта>");
    }
}