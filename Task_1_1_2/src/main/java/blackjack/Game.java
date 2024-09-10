package blackjack;

import java.util.Scanner;

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

    public void play() {
        System.out.println("Добро пожаловать в Блэкджек!");
        while (true) {
            playRound();
        }
        //scanner.close();
    }

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

    private void endRound(Player winner, String output) {
        winner.incScore();
        System.out.println(getScore(output));
        round++;
    }

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

    private String getScore(String startOutput) {
        String score = "Счёт " + player.getScore() + ":" + dealer.getScore();
        if (player.getScore() > dealer.getScore()) {
            score += " в вашу пользу";
        } else if (dealer.getScore() > player.getScore()) {
            score += " в пользу дилера";
        }
        return startOutput + score + '.';
    }

    private void exitWithLog(String error) {
        System.out.println(error);
        System.exit(1);
    }

    private String getCards(Player player) {
        return player.cards.toString();
    }

    private void getCardsStatus() {
        System.out.printf("Ваши карты: %s => %d\n", getCards(player), player.getCardScore());
        String dealerStatus = "Карты дилера: " + getCards(dealer);
        if (!dealer.getClosedCard().isClosed()) {
            dealerStatus += " => " + dealer.getCardScore();
        }
        System.out.println(dealerStatus);
        System.out.println();
    }

    private InputPlayerResult getPlayerResult() {
        int result = 0;
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        try {
            result = scanner.nextInt();
        } catch (Exception e) {
            exitWithLog("gg1");
        }
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

    private void playerMove() {
        System.out.println("Ваш ход\n-------");
        while (true) {
            switch (getPlayerResult()) {
                case END_MOVE -> {
                    return;
                }
                case CONTINUE_MOVE -> {
                    getCard(player, "Вы открыли карту");
                    if (player.isLoser()) {
                        return;
                    }
                }
            }
        }
    }

    private void dealerMove() {
        openDealerClosedCard();
        while (dealer.getCardScore() < BOUND_DEALER_SCORE) {
            getCard(dealer, "Дилер открывает карту");
        }
    }

    private void getCard(Player currentPlayer, String output) {
        Card card = currentPlayer.getCard(pack);
        System.out.printf("%s %s\n", output, card.toString());
        getCardsStatus();
    }

    private void openDealerClosedCard() {
        System.out.println("\nХод дилера\n-------");
        System.out.printf("Дилер открывает закрытую карту %s\n", dealer.openClosedCard().toString());
        getCardsStatus();
    }
}