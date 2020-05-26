import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    public static final int BOARD_LIMIT = 100;
    private final List<Ladder> ladders;
    private final List<Snake> snakes;
    private final Map<Player, Integer> playerPositions;

    public Board(List<Ladder> ladders, List<Snake> snakes, Set<Player> players) {
        this.ladders = ladders;
        this.snakes = snakes;
        playerPositions = players.stream().collect(Collectors.toMap(player -> player, player -> 0));
    }

    public void updatePositionFor(Player player, int diceOutcome) {
        Integer currentPosition = playerPositions.get(player);
        int nextPosition = currentPosition + diceOutcome;
        if (nextPosition > BOARD_LIMIT) {
            playerPositions.put(player, currentPosition);
            return;
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
        playerPositions.put(player, nextPosition);
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

    public int getPlayerPosition(Player player) {
        return playerPositions.get(player);
    }

}
