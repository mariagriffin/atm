package ie.atu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DepositSlotTest {
    @Test
    void simulationReceivesDepositEnvelope() {
        DepositSlot slot = new DepositSlot();

        assertTrue(slot.isEnvelopeReceived());
    }
}
