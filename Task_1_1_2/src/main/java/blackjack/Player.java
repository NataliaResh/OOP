package blackjack;

import java.util.ArrayList;

public class Player{
    private static final int ACE_WEIGHT = 12;
    public ArrayList<Card> cards = new ArrayList<>();
    int score;


    public void initPlayer(Pack pack) {
        score = 0;
        cards.clear();
        getCard(pack);
        getCard(pack);
    }

    public Card getCard(Pack pack) {
        Card card = pack.getCard();
        if (card.getWeight() == ACE_WEIGHT && score + ACE_WEIGHT > 21) {
            card.reduceWeight();
        }
        score += card.getWeight();
        cards.add(card);
        return card;
    }


}
