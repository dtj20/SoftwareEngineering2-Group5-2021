package newbank.server.test;

import newbank.server.Transaction;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;


public class TestTransaction {

    public Transaction createTransaction() {
        Transaction t = new Transaction("00000001", "00000002", "606060", 100);
        return t;
    }

    @Test
    public void testTransactionPayingAccount() {
        Transaction t = createTransaction();
        assertEquals("00000001", t.getPayingAccount());
    }

    @Test
    public void testTransactionRecAccount() {
        Transaction t = createTransaction();
        assertEquals("00000002", t.getReceivingAccount());
    }

    @Test
    public void testTransactionSort() {
        Transaction t = createTransaction();
        assertEquals("606060", t.getReceivingSort());
    }

    @Test
    public void testTransactionAmount() {
        Transaction t = createTransaction();
        assertTrue(100 == t.getAmount());
    }

    @Test
    public void testTransactionIdIsDifferent() {
        Transaction t = createTransaction();
        Transaction s = createTransaction();
        assertNotEquals(s.getTransactionID(), t.getTransactionID());
    }

    @Test
    public void testTransactionSummary() {
        Transaction t = createTransaction();
        String summary = t.getTransactionID() + "\n" +
                t.getAmount() + ": paid from " + t.getPayingAccount() + "\n"
                + "Paid on " + t.getTransactionDate() + "\n"
                + "Paid to Account Number: " + t.getReceivingAccount() + "\n"
                + "at Sort Code: " + t.getReceivingSort();
        System.out.println(summary);
        assertEquals(summary, t.getTransactionSummary());
    }




}
