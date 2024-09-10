package blackjack;

import java.util.Scanner;

/**
 * Class for managing Game.
 */
public class Game {
    private static final int BOUND_DEALER_SCORE = 17;

    private enum InputPlayerResult {
        END_MOVE,
        CONTINUE_MOVE
    }

    private int round = 1;
    private Pack pack;
    Player player = new Player();
    Dealer dealer = new Dealer();
    Scanner scanner = new Scanner(System.in);

    /**
     * Starts game.
     */
    public void play() {
        System.out.println("Добро пожаловать в Блэкджек!");
        while (true) {
            playRound();
        }
    }

    /**
     * Launches new round.
     */
    private void playRound() {
        System.out.printf("\nРаунд %d\n", round);

        pack = new Pack();

        player.initPlayer(pack);
        dealer.initPlayer(pack);

        System.out.println("Дилер раздал карты");
        getCardsStatus();
        if (checkBlackjack()) {
            return;
        }

        playerMove();
        if (player.isLoser()) {
            endRound(dealer, "Дилер выиграл раунд! ");
            return;
        }

        dealerMove();
        if (dealer.isLoser()) {
            endRound(player, "Вы выграли раунд! ");
            return;
        }
        checkWins();
    }

    /**
     * Finishes round.
     *
     * @param winner winner of current round;
     * @param output log of current round.
     */
    private void endRound(Player winner, String output) {
        winner.incScore();
        System.out.println(getScore(output));
        round++;
    }

    /**
     * Checks if player or dealer has blackjack.
     *
     * @return true if player or dealer has blackjack.
     */
    private boolean checkBlackjack() {
        if (!player.isWinner() || !dealer.isWinner()) {
            return false;
        }
        if (player.isWinner() && dealer.isWinner()) {
            dealer.incScore();
            endRound(player, "Ничья! ");
        } else if (player.isWinner()) {
            endRound(player, "Вы выграли раунд! ");
        } else {
            endRound(dealer, "Дилер выиграл раунд! ");
        }
        return true;
    }

    /**
     * Checks who is winner.
     */
    private void checkWins() {
        if (player.getCardScore() > dealer.getCardScore()) {
            endRound(player, "Вы выграли раунд! ");
        } else if (dealer.getCardScore() > player.getCardScore()) {
            endRound(dealer, "Дилер выиграл раунд! ");
        } else {
            dealer.incScore();
            endRound(player, "Ничья! ");
        }
    }

    /**
     * Returs score of current round.
     *
     * @param startOutput result of round;
     * @return score of current round.
     */
    private String getScore(String startOutput) {
        String score = "Счёт " + player.getScore() + ":" + dealer.getScore();
        if (player.getScore() > dealer.getScore()) {
            score += " в вашу пользу";
        } else if (dealer.getScore() > player.getScore()) {
            score += " в пользу дилера";
        }
        return startOutput + score + '.';
    }

    /**
     * Exits program with error.
     *
     * @param error happened error.
     */
    private void exitWithLog(String error) {
        System.out.println(error);
        System.exit(1);
    }

    /**
     * Prints player's and dealer's packs.
     */
    private void getCardsStatus() {
        System.out.printf("Ваши карты: %s => %d\n", player.getCards(), player.getCardScore());
        String dealerStatus = "Карты дилера: " + dealer.getCards();
        if (!dealer.getClosedCard().isClosed()) {
            dealerStatus += " => " + dealer.getCardScore();
        }
        System.out.println(dealerStatus + "\n");
    }

    /**
     * Gets user's input.
     *
     * @return user's decision.
     */
    private InputPlayerResult getPlayerResult() {
        int result = 0;
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        try {
            result = scanner.nextInt();
        } catch (Exception e) {
            exitWithLog("gg1");
        }
        switch (result) {
            case 0:
                return InputPlayerResult.END_MOVE;
            case 1:
                return InputPlayerResult.CONTINUE_MOVE;
            default:
                exitWithLog("gg");
                break;
        }
        return InputPlayerResult.END_MOVE;
    }

    /**
     * Manages player's move.
     */
    private void playerMove() {
        System.out.println("Ваш ход\n-------");
        while (true) {
            switch (getPlayerResult()) {
                case END_MOVE:
                    return;
                case CONTINUE_MOVE:
                    getCard(player, "Вы открыли карту");
                    if (player.isLoser()) return;
            }
        }
    }

    /**
     * Manages dealer's move.
     */
    private void dealerMove() {
        openDealerClosedCard();
        while (dealer.getCardScore() < BOUND_DEALER_SCORE) {
            getCard(dealer, "Дилер открывает карту");
        }
    }

    /**
     * Manages taking new card.
     *
     * @param currentPlayer player who taken new card;
     * @param output        start of log.
     */
    private void getCard(Player currentPlayer, String output) {
        Card card = currentPlayer.getCard(pack);
        System.out.printf("%s %s\n", output, card.toString());
        getCardsStatus();
    }

    /**
     * Manages opening dealer's closed card.
     */
    private void openDealerClosedCard() {
        System.out.println("\nХод дилера\n-------");
        System.out.printf("Дилер открывает закрытую карту %s\n",
                dealer.openClosedCard().toString());
        getCardsStatus();
    }
}