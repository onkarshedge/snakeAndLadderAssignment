import java.util.List;
import java.util.Optional;

public class Board {
    public static final int BOARD_LIMIT = 100;
    private final List<Ladder> ladders;

    public Board(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public int nextPosition(int currentPosition, int diceOutcome) {
        int nextPosition = currentPosition + diceOutcome;
        if (nextPosition > BOARD_LIMIT) {
            return currentPosition;
        }
        Optional<Ladder> optionalLadder = getLadderAt(nextPosition);
        return optionalLadder.map(ladder -> ladder.top).orElse(nextPosition);
    }

    private Optional<Ladder> getLadderAt(int position) {
        return ladders
                .stream()
                .filter(ladder -> position == ladder.foot)
                .findFirst();
    }

}
