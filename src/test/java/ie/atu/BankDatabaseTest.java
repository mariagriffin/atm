package ie.atu;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDatabaseTest {
    private ArrayList<Account> originalAccounts;
    private BankDatabase database;

    @BeforeEach
    void setUp() {
        // The database stores accounts statically; give each test its own list.
        originalAccounts = BankDatabase.accounts;
        BankDatabase.accounts = new ArrayList<>();
        database = new BankDatabase();
    }

    @AfterEach
    void tearDown() {
        BankDatabase.accounts = originalAccounts;
    }

    @Test
    void authenticatesExistingCustomerPins() {
        assertTrue(database.authenticateUser(11111));
        assertTrue(database.authenticateUser(2222299));
        assertTrue(database.authenticateUser(3333399));
    }

    @Test
    void rejectsUnknownPin() {
        assertFalse(database.authenticateUser(44444));
    }

    @Test
    void findsExistingAccount() {
        Account account = database.getAccount(98765);

        assertNotNull(account);
        assertEquals("Customer2", account.getUsername());
    }

    @Test
    void returnsNullForUnknownAccount() {
        assertNull(database.getAccount(55555));
    }

    @Test
    void creditChangesOnlySelectedAccountsTotalBalance() {
        database.credit(12345, 50.25);

        assertEquals(1250.25, database.getTotalBalance(12345), 0.0001);
        assertEquals(1000.0, database.getAvailableBalance(12345), 0.0001);
        assertEquals(200.0, database.getTotalBalance(98765), 0.0001);
    }

    @Test
    void debitChangesOnlySelectedAccountBalances() {
        database.debit(12345, 100.0);

        assertEquals(900.0, database.getAvailableBalance(12345), 0.0001);
        assertEquals(1100.0, database.getTotalBalance(12345), 0.0001);
        assertEquals(200.0, database.getAvailableBalance(98765), 0.0001);
        assertEquals(200.0, database.getTotalBalance(98765), 0.0001);
    }

    @Test
    void distinguishesManagerFromCustomer() {
        assertEquals(1, database.getadmin(0));
        assertEquals(0, database.getadmin(11111));
    }
}
