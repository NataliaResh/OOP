package blackjack;

import static blackjack.Player.ACE_WEIGHT;

/**
 * Class for managing card.
 */
public class Card {
    private final String name;
    private int weight;
    private boolean closed;

    /**
     * Constructor for Card.
     *
     * @param name   card's name;
     * @param weight card's weight.
     */
    public Card(String name, int weight) {
        this.name = name;
        if (weight < 0) {
            this.weight = 1;
        } else {
            this.weight = weight;
        }
    }

    /**
     * Returns card's {@link Card#weight}.
     *
     * @return card's weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Reduces card's {@link Card#weight} to one.
     */
    public void reduceWeight() {
        if (weight == ACE_WEIGHT) {
            weight = 1;
        }
    }

    /**
     * Returns string of {@link Card#name} and {@link Card#weight}.
     *
     * @return string of {@link Card#name} and {@link Card#weight}.
     */
    @Override
    public String toString() {
        if (closed) {
            return "<закрытая карта>";
        }
        return name + " (" + weight + ')';
    }

    /**
     * Opens closed card.
     */
    public void open() {
        closed = false;
    }

    /**
     * Closes opened card.
     */
    public void close() {
        closed = true;
    }

    /**
     * Checks if card is closed.
     *
     * @return true if card is closed.
     */
    public boolean isClosed() {
        return closed;
    }
}
