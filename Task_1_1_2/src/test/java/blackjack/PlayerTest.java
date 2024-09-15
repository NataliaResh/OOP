package blackjack;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

/**
 * Class for testing Player.
 */
public class PlayerTest {
    @Test
    public void initPlayerTest() {
        Pack pack = new Pack();
        Player player = new Player();
        player.initPlayer(pack);
        Assert.assertEquals(player.getScore(), 0);
        player.incScore();
        Assert.assertEquals(player.getScore(), 1);
    }
}
