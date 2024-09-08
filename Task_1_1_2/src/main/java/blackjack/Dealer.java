package blackjack;

public class Dealer extends Player{
    @Override
    public void initPlayer(Pack pack) {
        super.initPlayer(pack);
        cards.get(1).close();
    }

    public void openSecondCard() {
        //TODO: exception
        if (cards.size() != 2) return;
        cards.get(1).open();
    }
}
