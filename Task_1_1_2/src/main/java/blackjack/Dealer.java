package blackjack;

public class Dealer extends Player{
    @Override
    public void initPlayer(Pack pack) {
        super.initPlayer(pack);
        cards.get(1).close();
    }

    public Card openClosedCard() {
        Card card = getClosedCard();
        card.open();
        return card;
    }

    public Card getClosedCard() {
        //TODO: exception
        if (cards.size() < 2) return null;
        return cards.get(1);
    }
}
