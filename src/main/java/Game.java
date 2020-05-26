import java.util.Iterator;
import java.util.Set;

public class Game {
    private Board board;
    private Dice dice;
    private Set<Player> players;
    private Iterator<Player> playerIterator;
    public static final Player DEFAULT_PLAYER = new Player("DEFAULT_PLAYER");

    public Game(Board board, Dice dice, Set<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
        playerIterator = players.iterator();
    }


    void playTurn() {
        while (playerIterator.hasNext()) {
            int diceOutcome = dice.roll();
            board.updatePositionFor(playerIterator.next(), diceOutcome);
        }
        playerIterator = players.iterator();
    }

    int getPlayerPosition(Player player) {
        return board.getPlayerPosition(player);
    }
}
