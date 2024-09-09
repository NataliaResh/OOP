package blackjack;

import java.io.IOException;

public class Game {
    private static final int WIN_SCORE = 21;
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
        System.in.read();
        round++;
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

    private void playerMove() throws IOException {
        System.out.println("Ваш ход\n-------");
        while(true) {
            int result;
            // TODO: there is bug with many symbols!
            do {
                System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
                result = System.in.read();
            } while (result != '1' && result != '0');

            if (result == '0') break;
            Card card = player.getCard(pack);
            System.out.printf("Вы открыли карту %s\n", card.toString());
            getCardsStatus();
        }
    }

    private void dealerMove() {
        System.out.println("Ход дилера\n-------");
        dealer.openClosedCard();
        System.out.printf("Дилер открывает закрытую карту %s\n", dealer.getClosedCard().toString());
        getCardsStatus();
    }
}
