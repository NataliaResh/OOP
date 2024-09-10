package blackjack;

/**
 * Class for managing Dealer.
 */
public class Dealer extends Player{
    /**
     * Resets dealer's params for new round.
     * @param pack current {@code pack} of cards.
     */
    @Override
    public void initPlayer(Pack pack) {
        super.initPlayer(pack);
        cards.get(1).close();
    }

    /**
     * Opens closed second dealer's card.
     * @return current opened card.
     */
    public Card openClosedCard() {
        Card card = getClosedCard();
        card.open();
        return card;
    }

    /**
     * Returns closed second dealer's card.
     * @return closed second dealer's card.
     */
    public Card getClosedCard() {
        //TODO: exception
        if (cards.size() < 2) return null;
        return cards.get(1);
    }
}
