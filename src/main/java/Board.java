import java.util.List;
import java.util.Optional;

public class Board {
    public static final int BOARD_LIMIT = 100;
    private final List<Ladder> ladders;
    private final List<Snake> snakes;


    public Board(List<Ladder> ladders, List<Snake> snakes) {
        this.ladders = ladders;
        this.snakes = snakes;
    }

    public int nextPosition(int currentPosition, int diceOutcome) {
        int nextPosition = currentPosition + diceOutcome;
        if (nextPosition > BOARD_LIMIT) {
            return currentPosition;
        }

        while (currentPosition != nextPosition) {
            currentPosition = nextPosition;
            Optional<Ladder> optionalLadder = getLadderAt(nextPosition);
            Optional<Snake> optionalSnake = getSnakeAt(nextPosition);

            if (optionalSnake.isPresent()) {
                nextPosition = optionalSnake.get().tail;
            } else if (optionalLadder.isPresent()) {
                nextPosition = optionalLadder.get().top;
            }
        }
        return nextPosition;
    }

    private Optional<Ladder> getLadderAt(int position) {
        return ladders
                .stream()
                .filter(ladder -> position == ladder.foot)
                .findFirst();
    }

    private Optional<Snake> getSnakeAt(int position) {
        return snakes
                .stream()
                .filter(snake -> position == snake.head)
                .findFirst();
    }


}
