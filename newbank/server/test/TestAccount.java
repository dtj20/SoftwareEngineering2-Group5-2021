package newbank.server.test;

import newbank.server.Account;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestAccount {

    @Test
    public void testAccountCreation() {
        String Name = "Test Account";
        int Balance = 1500;
        Account acc = new Account(Name, Balance );
        assertEquals(Name, acc.getName());
        assertTrue(Balance == acc.getBalance());
    }

    @Test
    public void testToString() {
        String name = "name";
        int balance = 1500;
        String expected =  name + ": " + balance;
        Account acc = new Account(name, balance);
        assertEquals(expected, acc.toString());
    }



}
