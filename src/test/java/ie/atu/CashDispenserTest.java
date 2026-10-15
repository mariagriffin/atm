package ie.atu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashDispenserTest {
    private CashDispenser dispenser;

    @BeforeEach
    void setUp() {
        dispenser = new CashDispenser();
    }

    @Test
    void initiallyContainsTenThousand() {
        assertTrue(dispenser.isSufficientCashAvailable(10000));
        assertFalse(dispenser.isSufficientCashAvailable(10020));
    }

    @Test
    void dispensingCashReducesAvailableCash() {
        dispenser.dispenseCash(60);

        assertTrue(dispenser.isSufficientCashAvailable(9940));
        assertFalse(dispenser.isSufficientCashAvailable(9960));
    }

    @Test
    void multipleWithdrawalsAccumulate() {
        dispenser.dispenseCash(40);
        dispenser.dispenseCash(60);

        assertTrue(dispenser.isSufficientCashAvailable(9900));
        assertFalse(dispenser.isSufficientCashAvailable(9920));
    }

    @Test
    void emptyDispenserCannotSupplyAnotherBill() {
        dispenser.dispenseCash(10000);

        assertFalse(dispenser.isSufficientCashAvailable(20));
        assertTrue(dispenser.isSufficientCashAvailable(0));
    }
}
