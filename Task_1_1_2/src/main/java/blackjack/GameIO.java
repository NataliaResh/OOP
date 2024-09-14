package blackjack;

import java.util.Scanner;

import static blackjack.Game.*;

/**
 * Class for managing I/O.
 */
public class GameIO {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Returns user's input.
     *
     * @return true if user want to continue move.
     */
    public static boolean getPlayerInput() {
        while (true) {
            System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
            try {
                switch (Integer.parseInt(scanner.next())) {
                    case 0:
                        return false;
                    case 1:
                        return true;
                    default:
                        System.out.println("Неверный ввод, попробуйте ещё раз!");
                }
            } catch (Exception ignored) {
                System.out.println("Неверный ввод, попробуйте ещё раз!");
            }
        }
    }

    /**
     * Prints player's and dealer's packs.
     *
     * @param player player;
     * @param dealer dealer.
     */
    public static void printCardsStatus(Player player, Dealer dealer) {
        System.out.printf("Ваши карты: %s => %d\n", player.getCards(), player.getCardScore());
        String dealerStatus = "Карты дилера: " + dealer.getCards();
        if (!dealer.getClosedCard().isClosed()) {
            dealerStatus += " => " + dealer.getCardScore();
        }
        System.out.println(dealerStatus + "\n");
    }

    /**
     * Returns score of current round.
     *
     * @param result result of round;
     * @param player player;
     * @param dealer dealer.
     */
    public static void printScore(Game.RoundResult result, Player player, Dealer dealer) {
        String score = "";
        switch (result) {
            case DEALER_WIN:
                score += "Дилер выиграл раунд! ";
                break;
            case PLAYER_WIN:
                score += "Вы выграли раунд! ";
                break;
            case DRAW:
                score += "Ничья!";
                break;
            default:
                break;
        }
        score += "Счёт " + player.getScore() + ":" + dealer.getScore();
        if (player.getScore() > dealer.getScore()) {
            score += " в вашу пользу";
        } else if (dealer.getScore() > player.getScore()) {
            score += " в пользу дилера";
        }
        System.out.println(score + '.');
    }

    /**
     * Prints message about starting new round.
     *
     * @param round number of round.
     */
    public static void printStartRound(int round) {
        System.out.printf("\nРаунд %d\n", round);
        System.out.println("Дилер раздал карты");
    }

    /**
     * Prints information about start game.
     */
    public static void printStartGame() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    /**
     * Prints information about player's move.
     *
     * @param player current player.
     */
    public static void printMove(PlayerType player) {
        switch (player) {
            case PLAYER:
                System.out.println("Ваш ход\n-------");
                break;
            case DEALER:
                System.out.println("\nХод дилера\n-------");
                System.out.println();
            default:
                break;
        }
    }

    /**
     * Prints information about received card.
     *
     * @param card     new card;
     * @param player   current player;
     * @param isClosed true if card is closed.
     */
    public static void printGetCard(Card card, PlayerType player, boolean isClosed) {
        String output = "";
        switch (player) {
            case PLAYER:
                output = "Вы открыли карту ";
                break;
            case DEALER:
                if (!isClosed) {
                    output = "Дилер открывает карту ";
                } else {
                    output = "Дилер открывает закрытую карту ";
                }
            default:
                break;
        }
        System.out.println(output + card.toString());
    }
}
