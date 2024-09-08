package blackjack;

public class Card {
    private final String name;
    private int weight;
    private boolean closed;

    public Card(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Card(String name, int weight, boolean closed) {
        this(name, weight);
        this.closed = closed;
    }

    public int getWeight() {
        return weight;
    }

    public void reduceWeight() {
        weight = 1;
    }

    @Override
    public String toString() {
        if (closed) return "<закрытая карта>";
        return name + " (" + weight + ')';
    }

    public void open() {
        closed = false;
    }

    public void close() {
        closed = true;
    }
}
