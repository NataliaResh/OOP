package blackjack;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    private static final int WIN_SCORE = 21;
    private static final int BOUND_DEALER_SCORE = 17;

    private enum InputPlayerResult {
        END_MOVE,
        CONTINUE_MOVE
    }

    private int round = 1;
    private Pack pack;

    Player player = new Player();
    Dealer dealer = new Dealer();

    public Game() {

    }

    public void start() throws IOException {
        while (true) {
            playRound();

        }
    }

    private void playRound() throws IOException {
        System.out.printf("Раунд %d\n", round);

        pack = new Pack();

        player.initPlayer(pack);
        dealer.initPlayer(pack);

        System.out.println("Дилер раздал карты");

        getCardsStatus();

        playerMove();

        dealerMove();

        round++;
    }

    private void exitWithLog(String error) {
        System.out.println(error);
        System.exit(1);
    }

    private String getCards(Player player) {
        return player.cards.toString();
    }

    private void getCardsStatus() {
        System.out.printf("Ваши карты: %s => %d\n", getCards(player), player.getScore());
        String dealerStatus = "Карты дилера: " + getCards(dealer);
        if (!dealer.getClosedCard().isClosed()) {
            dealerStatus += " => " + dealer.getScore();
        }
        System.out.println(dealerStatus);
        System.out.println();
    }

    private InputPlayerResult getPlayerResult() {
        int result = 0;
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        Scanner scanner = new Scanner(System.in);
        try {
            result = scanner.nextInt();
        } catch (Exception e) {
            exitWithLog("gg");
        }
        scanner.close();
        switch (result) {
            case 0 -> {
                return InputPlayerResult.END_MOVE;
            }
            case 1 -> {
                return InputPlayerResult.CONTINUE_MOVE;
            }
            default -> exitWithLog("gg");
        }
        return InputPlayerResult.END_MOVE;
    }

    private void playerMove() throws IOException {
        System.out.println("Ваш ход\n-------");
        while (true) {
            switch (getPlayerResult()) {
                case END_MOVE -> {
                    return;
                }
                case CONTINUE_MOVE -> getCard(player, "Вы открыли карту");
            }
        }
    }


    private void dealerMove() {
        openDealerClosedCard();
        while (dealer.getScore() < BOUND_DEALER_SCORE) {
            getCard(dealer, "Дилер открывает карту");
        }
    }

    private void getCard(Player currentPlayer, String output) {
        Card card = currentPlayer.getCard(pack);
        System.out.printf("%s %s\n", output, card.toString());
        getCardsStatus();
    }

    private void openDealerClosedCard() {
        System.out.println("Ход дилера\n-------");
        dealer.openClosedCard();
        System.out.printf("Дилер открывает закрытую карту %s\n", dealer.getClosedCard().toString());
        getCardsStatus();
    }
}