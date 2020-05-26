public class Board {
    public static final int BOARD_LIMIT = 100;

    public int nextPosition(int currentPosition, int diceOutcome) {
        int nextPosition = currentPosition + diceOutcome;
        if (nextPosition > BOARD_LIMIT) {
            return currentPosition;
        }
        return nextPosition;
    }
}
