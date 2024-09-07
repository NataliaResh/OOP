public class Game {
    private int round = 1;

    public Game() {
        Player player = new Player();
        Player diller = new Player();
    }

    public void start() {
        while (true) {
            System.out.printf("Раунд {round}");
            round++;
        }
    }
}
