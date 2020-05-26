import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NormalDiceTest {

    @Test
    public void shouldReturnNumbers1to6() {
        CrookedDice crookedDice = new CrookedDice();

        int diceOutCome = crookedDice.roll();

        assertTrue(diceOutCome <= 6);
        assertTrue(diceOutCome >= 1);
    }
}