package newbank.server.test;

import newbank.server.Customer;
import newbank.server.Account;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestCustomer {

    @Test
    public void testCustomerAccounts() {
        Customer c = new Customer();
        String empty = "";
        assertEquals("Customer is not empty", empty, c.accountsToString());
    }

    @Test
    public void testCustomerAddAccount() {
        Customer c = new Customer();
        String empty = "";
        Account acc = new Account("name", 1500);
        c.addAccount(acc);
        assertNotEquals("Customer is empty", empty, c.accountsToString());
    }

}
