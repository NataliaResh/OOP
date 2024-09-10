package blackjack;

import java.util.ArrayList;

public class Player {
    private static final int ACE_WEIGHT = 11;
    private static final int WIN_SCORE = 21;
    public ArrayList<Card> cards = new ArrayList<>();
    private int cardScore;

     private int score;

    public void initPlayer(Pack pack) {
        cardScore = 0;
        cards.clear();
        getCard(pack);
        getCard(pack);
    }

    public Card getCard(Pack pack) {
        Card card = pack.getCard();
        if (card.getWeight() == ACE_WEIGHT && cardScore + ACE_WEIGHT > 21) {
            card.reduceWeight();
        }
        cardScore += card.getWeight();
        cards.add(card);
        return card;
    }

    public int getCardScore() {
        return cardScore;
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        score++;
    }

    public boolean isWinner() {
        return cardScore == WIN_SCORE;
    }

    public boolean isLoser() {
        return cardScore > WIN_SCORE;
    }
}
