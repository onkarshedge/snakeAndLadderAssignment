import java.util.Random;

public class NormalDice implements Dice {
    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}
