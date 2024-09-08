package blackjack;

public class Card {
    private final String name;
    private int weight;

    public Card(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void reduceWeight() {
        weight = 1;
    }

    @Override
    public String toString() {
        return name + " (" + weight + ')';
    }
}
