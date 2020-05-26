import java.util.Random;

public class CrookedDice implements Dice {
    @Override
    public int roll() {
        Random random = new Random();
        return (random.nextInt(3) + 1) * 2;
    }
}
