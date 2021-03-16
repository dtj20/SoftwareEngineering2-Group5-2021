package newbank.server.test;

import newbank.server.Customer;
import newbank.server.Account;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestCustomer {

    @Test
    public void testCustomerAccounts() {
        Customer c = new Customer("1234");
        String empty = "";
        assertEquals("Customer is not empty", empty, c.accountsToString());
    }

    @Test
    public void testCustomerAddAccount() {
        Customer c = new Customer("1234");
        String empty = "";
        Account acc = new Account("name", 1500);
        c.addAccount(acc);
        assertNotEquals("Customer is empty", empty, c.accountsToString());
    }

    @Test
    public void testFindAccount() {
        Customer c = new Customer("1234");
        Account acc = new Account("name", 1500);
        c.addAccount(acc);
        Account test = c.findAccount("name");
        assertEquals("accounts are not equal", acc, test);
    }

    @Test
    public void testPasswordSet() {
        String password = "1234";
        Customer c = new Customer(password);
        assertEquals("passwords do not match", c.getPassword(), password);
    }

}
