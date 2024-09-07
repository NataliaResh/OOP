package blackjack;

public class Card {
    private final String name;
    private final int weight;

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
}
