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
        Optional<Ladder> optionalLadder = getLadderAt(nextPosition);
        Optional<Snake> optionalSnake = getSnakeAt(nextPosition);
        return optionalLadder.map(ladder -> ladder.top)
                .orElse(
                        optionalSnake.map(snake -> snake.tail).orElse(nextPosition)
                );
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
