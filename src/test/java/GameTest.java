import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertNotEquals;

public class GameTest {

    Player RED = new Player("red");
    Player GREEN = new Player("green");
    Player BLUE = new Player("blue");

    @Test
    public void shouldUpdatePositionsOfEachPlayerInATurn() {
        Set<Player> players = Set.of(RED, GREEN, BLUE);
        Game game = new GameBuilder()
                .with(gameBuilder -> {
                    gameBuilder.players = players;
                    gameBuilder.dice = new CrookedDice();
                })
                .createGame();

        game.playTurn();

        assertNotEquals(0, game.getPlayerPosition(RED));
        assertNotEquals(0, game.getPlayerPosition(GREEN));
        assertNotEquals(0, game.getPlayerPosition(BLUE));
    }

    @Test
    public void shouldUpdateUpdateDefaultPlayerPosition() {
        Game game = new GameBuilder().createGame();

        game.playTurn();

        assertNotEquals(0, Game.DEFAULT_PLAYER);
    }
}