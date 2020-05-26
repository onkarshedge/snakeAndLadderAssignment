import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void shouldReturnNextPositionAs9() {
        Board board = new Board();
        int currentPosition = 4;
        int diceOutcome = 5;

        int actualPosition = board.nextPosition(currentPosition, diceOutcome);

        assertEquals(9, actualPosition);
    }
}