package newbank.server.test;

import newbank.server.Deposit;
import newbank.server.Source;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestDeposit {

    public Deposit createDeposit() {

        Deposit d = new Deposit("account", "100", "account", 100, Source.Internal);
        return d;
    }

    @Test
    public void testDepositPayingAccount() {
        Deposit d = createDeposit();
        assertEquals("account", d.getPayingAccount());
    }

    @Test
    public void testTransactionRecAccount() {
        Deposit d = createDeposit();
        assertEquals("account", d.getRecieveingAccount());
    }

    @Test
    public void testTransactionSort() {
        Deposit d = createDeposit();
        assertEquals("100", d.getPayingSort());
    }

    @Test
    public void testTransactionAmount() {
        Deposit d = createDeposit();
        assertTrue(100 == d.getAmount());
    }

    @Test
    public void testTransactionIdIsDifferent() {
        Deposit d = createDeposit();
        Deposit e = createDeposit();
        assertNotEquals(d.getDepositID(), e.getDepositID());
    }
}
