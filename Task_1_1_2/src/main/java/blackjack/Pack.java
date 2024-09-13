package blackjack;

import static blackjack.GameIO.exit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for managing Pack of cards.
 */
public class Pack {
    private ArrayList<Card> packCards = new ArrayList<>(List.of(
            new Card("Двойка Пики", 2), new Card("Тройка Пики", 3),
            new Card("Четвёрка Пики", 4), new Card("Пятёрка Пики", 5),
            new Card("Шестёрка Пики", 6), new Card("Семёрка Пики", 7),
            new Card("Восьмёрка Пики", 8), new Card("Девятка Пики", 9),
            new Card("Десятка Пики", 10), new Card("Валет Пики", 10),
            new Card("Пиковая Дама", 10), new Card("Король Пики", 10),
            new Card("Туз Пики", 11),
            new Card("Двойка Буби", 2), new Card("Тройка Буби", 3),
            new Card("Четвёрка Буби", 4), new Card("Пятёрка Буби", 5),
            new Card("Шестёрка Буби", 6), new Card("Семёрка Буби", 7),
            new Card("Восьмёрка Буби", 8), new Card("Девятка Буби", 9),
            new Card("Десятка Буби", 10), new Card("Валет Буби", 10),
            new Card("Бубновая Дама", 10), new Card("Король Буби", 10),
            new Card("Туз Буби", 11),
            new Card("Двойка Черви", 2), new Card("Тройка Черви", 3),
            new Card("Четвёрка Черви", 4), new Card("Пятёрка Черви", 5),
            new Card("Шестёрка Черви", 6), new Card("Семёрка Черви", 7),
            new Card("Восьмёрка Черви", 8), new Card("Девятка Черви", 9),
            new Card("Десятка Черви", 10), new Card("Валет Черви", 10),
            new Card("Червовая Дама", 10), new Card("Король Черви", 10),
            new Card("Туз Черви", 11),
            new Card("Двойка Трефы", 2), new Card("Тройка Трефы", 3),
            new Card("Четвёрка Трефы", 4), new Card("Пятёрка Трефы", 5),
            new Card("Шестёрка Трефы", 6), new Card("Семёрка Трефы", 7),
            new Card("Восьмёрка Трефы", 8), new Card("Девятка Трефы", 9),
            new Card("Десятка Трефы", 10), new Card("Валет Трефы", 10),
            new Card("Дама Трефы", 10), new Card("Король Трефы", 10),
            new Card("Туз Трефы", 11)));

    Random generator = new Random();

    /**
     * Takes next pseudo-random card from {@link Pack#packCards}.
     *
     * @return next pseudo-random card from {@link Pack#packCards}.
     */
    public Card getCard() {
        if (packCards.isEmpty()) {
            exit("Pack is empty!");
        }
        int index = generator.nextInt(packCards.size());
        Card currentCard = packCards.get(index);
        packCards.remove(index);
        return currentCard;
    }
}
