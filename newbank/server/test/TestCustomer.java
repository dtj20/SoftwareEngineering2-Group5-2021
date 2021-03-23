package newbank.server.test;

import newbank.server.Account;
import newbank.server.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCustomer {

    @Test
    public void testCustomerAccounts() {
        Customer c = new Customer("John","1234", "jojoba");
        String empty = "";
        assertEquals("Customer is not empty", empty, c.accountsToString());
    }

    @Test
    public void testCustomerAddAccount() {
        Customer c = new Customer("John","1234", "jojoba");
        String empty = "";
        Account acc = new Account("name", 1500);
        c.addAccount(acc);
        assertNotEquals("Customer is empty", empty, c.accountsToString());
    }

    @Test
    public void testFindAccount() {
        Customer c = new Customer("John","1234", "jojoba");
        Account acc = new Account("name", 1500);
        c.addAccount(acc);
        Account test = c.findAccount("name");
        assertEquals("accounts are not equal", acc, test);
    }

    @Test
    public void testPasswordSet() {
        String name = "John";
        String password = "1234";
        String memorableWord = "jojoba";
        Customer c = new Customer(name, password, memorableWord);
        assertEquals("passwords do not match", c.getPassword(), password);
    }

}
