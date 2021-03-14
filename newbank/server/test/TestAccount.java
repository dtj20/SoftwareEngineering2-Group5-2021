package newbank.server.test;

import newbank.server.Account;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TestAccount {

    @Test
    public void testAccountCreation() {
        String Name = "Test Account";
        int Balance = 1500;
        Account acc = new Account(Name, Balance );
        //assertEquals(Name, acc.getName());
        //assertEquals(Balance, acc.getBalance());

    }
}
