package blackjack;

public class Game {
    private static final int WIN_SCORE = 21;
    private int round = 1;
    public Pack pack;

    Player player = new Player();
    Player dealer = new Player();

    public Game() {

    }

    public void start() {
        while (true) {
            playRound();

        }
    }

    public void playRound() {
        System.out.printf("Раунд %d\n", round);
        pack = new Pack();
        player.initPlayer(pack);
        dealer.initPlayer(pack);
        System.out.println("Дилер раздал карты");

        round++;
    }

    public String getCards(Player player) {
        return getCards(player, false);
    }
    public String getCards(Player player, boolean secondClosed) {
        String listCards = player.cards.toString();
        return "";
    }
}
