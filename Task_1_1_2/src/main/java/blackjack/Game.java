package blackjack;

import static blackjack.GameIO.*;

/**
 * Class for managing Game.
 */
public class Game {
    private static final int BOUND_DEALER_SCORE = 17;

    public enum PlayerType {
        PLAYER,
        DEALER
    }

    public enum RoundResult {
        PLAYER_WIN,
        DEALER_WIN,
        DRAW
    }

    private int round = 1;
    private Pack pack;
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();

    /**
     * Starts game.
     */
    public void play() {
        printStartGame();
        while (true) {
            playRound();
        }
    }

    /**
     * Launches new round.
     */
    private void playRound() {
        pack = new Pack();
        player.initPlayer(pack);
        dealer.initPlayer(pack);

        printStartRound(round);
        printCardsStatus(player, dealer);
        if (checkBlackjack()) {
            return;
        }
        playerMove();
        if (player.isLoser()) {
            endRound(dealer, RoundResult.DEALER_WIN);
            return;
        }

        dealerMove();
        if (dealer.isLoser()) {
            endRound(player, RoundResult.PLAYER_WIN);
            return;
        }
        checkWins();
    }

    /**
     * Finishes round.
     *
     * @param winner winner of current round;
     * @param result result of round.
     */
    private void endRound(Player winner, RoundResult result) {
        if (winner != null) {
            winner.incScore();
            round++;
        }
        printScore(result, player, dealer);
    }

    /**
     * Finishes round with no winner.
     */
    private void endRound() {
        endRound(null, RoundResult.DRAW);
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
            endRound();
        } else if (player.isWinner()) {
            endRound(player, RoundResult.PLAYER_WIN);
        } else {
            endRound(dealer, RoundResult.DEALER_WIN);
        }
        return true;
    }

    /**
     * Checks who is winner.
     */
    private void checkWins() {
        if (player.getCardScore() > dealer.getCardScore()) {
            endRound(player, RoundResult.PLAYER_WIN);
        } else if (dealer.getCardScore() > player.getCardScore()) {
            endRound(dealer, RoundResult.DEALER_WIN);
        } else {
            endRound();
        }
    }

    /**
     * Manages player's move.
     */
    private void playerMove() {
        printMove(PlayerType.PLAYER);
        while (getPlayerInput()) {
            getCard(PlayerType.PLAYER, player);
            if (player.isLoser() || player.isWinner()) {
                return;
            }
        }
    }

    /**
     * Manages dealer's move.
     */
    private void dealerMove() {
        openDealerClosedCard();
        while (dealer.getCardScore() < BOUND_DEALER_SCORE) {
            getCard(PlayerType.DEALER, dealer);
        }
    }

    /**
     * Manages taking new card.
     *
     * @param type          type of player;
     * @param currentPlayer player who taken new card.
     */
    private void getCard(PlayerType type, Player currentPlayer) {
        Card card = currentPlayer.getCard(pack);
        printGetCard(card, type, false);
        printCardsStatus(player, dealer);
    }

    /**
     * Manages opening dealer's closed card.
     */
    private void openDealerClosedCard() {
        Card card = dealer.openClosedCard();
        printGetCard(card, PlayerType.DEALER, true);
        printCardsStatus(player, dealer);
    }
}