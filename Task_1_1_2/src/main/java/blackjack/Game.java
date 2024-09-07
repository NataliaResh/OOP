package blackjack;

public class Game {
    private static final int WIN_SCORE = 21;
    private int round = 1;


    public Pack pack;

    public Game() {
        Player player = new Player();
        Player diller = new Player();
    }

    public void start() {
        while (true) {
            playRound();

        }
    }

    public void playRound() {
        System.out.printf("Раунд %d", round);
        pack = new Pack();
        System.out.print("Дилер раздал карты");

        round++;
    }
}
