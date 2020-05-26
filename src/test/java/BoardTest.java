import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    static final Player red = new Player("RED");
    Set<Player> players = Set.of(red);

    @Test
    public void shouldReturnNextPositionAs9() {
        Board board = new Board(Collections.emptyList(), Collections.emptyList(), players);
        movePlayerToPosition(red, board, 4);
        int diceOutcome = 5;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(9, board.getPlayerPosition(red));
    }

    @Test
    public void shouldReturnNextPositionAsSameWhenNextPositionIsOutsideBoard() {
        Board board = new Board(Collections.emptyList(), Collections.emptyList(), players);
        movePlayerToPosition(red, board, 96);
        int diceOutcome = 5;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(96, board.getPlayerPosition(red));
    }

    @Test
    public void shouldReturnNextPositionAsLadderTop() {
        Board board = new Board(List.of(new Ladder(37, 85)), Collections.emptyList(), players);
        movePlayerToPosition(red, board, 34);
        int diceOutcome = 3;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(85, board.getPlayerPosition(red));
    }

    @Test
    public void shouldReturnNextPositionAsSnakeTail() {
        Board board = new Board(
                List.of(new Ladder(37, 85)),
                List.of(new Snake(65, 35)),
                players);
        movePlayerToPosition(red, board, 60);
        int diceOutcome = 5;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(35, board.getPlayerPosition(red));
    }

    @Test
    public void shouldReturnNextPositionAsLadderTopIfReachedHereBySnakeTail() {
        Board board = new Board(
                List.of(
                        new Ladder(37, 85),
                        new Ladder(35, 55)
                ),
                List.of(new Snake(65, 35)),
                players
        );
        movePlayerToPosition(red, board, 60);
        int diceOutcome = 5;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(55, board.getPlayerPosition(red));
    }

    @Test
    public void shouldCheckForLadderOrSnakeUntilNoPositionChange() {
        Board board = new Board(
                List.of(
                        new Ladder(37, 85),
                        new Ladder(35, 55),
                        new Ladder(55, 95)
                ),
                List.of(
                        new Snake(65, 35),
                        new Snake(95, 21)
                ),
                players
        );
        movePlayerToPosition(red, board, 60);
        int diceOutcome = 5;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(21, board.getPlayerPosition(red));
    }

    @Test
    public void shouldUpdatePositionOnlyForCurrentPlayer() {
        Player green = new Player("Green");
        Board board = new Board(
                List.of(
                        new Ladder(37, 85),
                        new Ladder(35, 55)
                ),
                List.of(
                        new Snake(65, 35),
                        new Snake(95, 21)
                ),
                Set.of(red, green)
        );
        movePlayerToPosition(green, board, 60);
        movePlayerToPosition(red, board, 30);
        int diceOutcome = 5;

        board.updatePositionFor(red, diceOutcome);

        assertEquals(55, board.getPlayerPosition(red));
        assertEquals(60, board.getPlayerPosition(green));
    }

    private void movePlayerToPosition(Player player, Board board, int position) {
        board.updatePositionFor(player, position);
    }

}