import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void shouldReturnNextPositionAs9() {
        Board board = new Board(Collections.emptyList(), Collections.emptyList());
        int currentPosition = 4;
        int diceOutcome = 5;

        int actualPosition = board.nextPosition(currentPosition, diceOutcome);

        assertEquals(9, actualPosition);
    }

    @Test
    public void shouldReturnNextPositionAsSameWhenNextPositionIsOutsideBoard() {
        Board board = new Board(Collections.emptyList(), Collections.emptyList());
        int currentPosition = 96;
        int diceOutcome = 5;

        int actualPosition = board.nextPosition(currentPosition, diceOutcome);

        assertEquals(96, actualPosition);
    }

    @Test
    public void shouldReturnNextPositionAsLadderTop() {
        Board board = new Board(List.of(new Ladder(37, 85)), Collections.emptyList());
        int currentPosition = 34;
        int diceOutcome = 3;

        int actualPosition = board.nextPosition(currentPosition, diceOutcome);

        assertEquals(85, actualPosition);
    }

    @Test
    public void shouldReturnNextPositionAsSnakeTail() {
        Board board = new Board(List.of(new Ladder(37, 85)), List.of(new Snake(65, 35)));
        int currentPosition = 60;
        int diceOutcome = 5;

        int actualPosition = board.nextPosition(currentPosition, diceOutcome);

        assertEquals(35, actualPosition);
    }

}