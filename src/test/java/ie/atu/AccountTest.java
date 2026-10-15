package ie.atu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("Customer", 12345, 11111, 1000.0, 1200.0, 0);
    }

    @Test
    void acceptsMatchingPin() {
        assertTrue(account.validatePIN(1111199));
    }

    @Test
    void rejectsIncorrectPin() {
        assertFalse(account.validatePIN(2222299));
    }

    @Test
    void depositIncreasesTotalBalanceButRemainsUnavailable() {
        account.credit(125.50);

        assertEquals(1325.50, account.getTotalBalance(), 0.0001);
        assertEquals(1000.0, account.getAvailableBalance(), 0.0001);
    }

    @Test
    void withdrawalReducesBothBalances() {
        account.debit(200.0);

        assertEquals(800.0, account.getAvailableBalance(), 0.0001);
        assertEquals(1000.0, account.getTotalBalance(), 0.0001);
    }

    @Test
    void canWithdrawEntireAvailableBalance() {
        account.debit(1000.0);

        assertEquals(0.0, account.getAvailableBalance(), 0.0001);
        assertEquals(200.0, account.getTotalBalance(), 0.0001);
    }
}
