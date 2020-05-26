import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class GameBuilder {
    public Dice dice = new NormalDice();
    public Set<Player> players = Set.of(Game.DEFAULT_PLAYER);
    public Board board;

    public GameBuilder with(Consumer<GameBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }

    public Game createGame() {
        if (board == null) {
            board = new Board(
                    List.of(
                            new Ladder(3, 51),
                            new Ladder(6, 27),
                            new Ladder(20, 70),
                            new Ladder(36, 55),
                            new Ladder(63, 95),
                            new Ladder(68, 98)
                    ),
                    List.of(
                            new Snake(34, 1),
                            new Snake(65, 52),
                            new Snake(47, 19),
                            new Snake(25, 5),
                            new Snake(87, 57),
                            new Snake(99, 69),
                            new Snake(91, 61)
                    ),
                    players
            );
        }
        return new Game(board, dice, players);
    }
}