package blackjack;

import java.util.ArrayList;

public class Player {
    private static final int ACE_WEIGHT = 12;
    public ArrayList<Card> cards;
    int score = 0;

    public Player() {
        // generates firsts cards
    }

    public void initPlayer(Pack pack) {
        cards.clear();
        getCard(pack);
        getCard(pack);
    }

    private void getCard(Pack pack) {
        Card card = pack.getCard();
        if (card.getWeight() == ACE_WEIGHT && score + ACE_WEIGHT > 21) {
            card.reduceWeight();
        }
        score += card.getWeight();
        cards.add(card);
    }


}
