package blackjack;

import java.util.ArrayList;

/**
 * Class for managing Player.
 */
public class Player {
    public static final int ACE_WEIGHT = 11;
    private static final int WIN_SCORE = 21;
    public ArrayList<Card> cards = new ArrayList<>();
    private int cardScore;
    private int score;

    /**
     * Resets player's params for new round.
     *
     * @param pack current {@code pack} of cards.
     */
    public void initPlayer(Pack pack) {
        cardScore = 0;
        cards.clear();
        getCard(pack);
        getCard(pack);
    }

    /**
     * Takes new card from {@code pack} and adds it to player's pack.
     *
     * @param pack current {@code pack} of cards.
     * @return new {@code} card from {@code pack}.
     */
    public Card getCard(Pack pack) {
        Card card = pack.getCard();
        if (card.getWeight() == ACE_WEIGHT && cardScore + ACE_WEIGHT > 21) {
            card.reduceWeight();
        }
        cardScore += card.getWeight();
        cards.add(card);
        return card;
    }

    /**
     * Returns score of player's pack.
     *
     * @return score of player's pack.
     */
    public int getCardScore() {
        return cardScore;
    }

    /**
     * Returns player's score (count of wins).
     *
     * @return player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Decrease player's score by one.
     */
    public void incScore() {
        score++;
    }

    /**
     * Returns list of player's cards.
     *
     * @return list of player's cards.
     */
    public String getCards() {
        return cards.toString();
    }

    /**
     * Checks if player is winner.
     *
     * @return true if player is winner.
     */
    public boolean isWinner() {
        return cardScore == WIN_SCORE;
    }

    /**
     * Checks if player is loser.
     *
     * @return true if player is loser.
     */
    public boolean isLoser() {
        return cardScore > WIN_SCORE;
    }
}
