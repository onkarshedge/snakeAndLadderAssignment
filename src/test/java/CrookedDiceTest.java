import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CrookedDiceTest {

    @Test
    public void shouldReturnOnlyEvenNumbers() {
        CrookedDice crookedDice = new CrookedDice();

        int diceOutCome = crookedDice.roll();

        assertEquals(0, diceOutCome % 2);
        assertTrue(diceOutCome <= 6);
    }
}