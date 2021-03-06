package newbank.server.test;

import newbank.server.Account;
import newbank.server.Customer;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestAccount {

    @Test
    public void testAccountCreation() {
        String Name = "Test Account";
        double Balance = 1500;
        int sort = 203045;
        int account = 12345678;
        String IBAN = "GB" + account + "1010";
        String cr;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date created = Calendar.getInstance().getTime();
        cr = dateFormat.format(created);


        Account acc = new Account(Name, Balance);
        assertEquals(Name, acc.getName());
        assertEquals(sort, acc.getSort());
        assertEquals(IBAN.length(), acc.getIBAN().length());
        assertEquals((int) (Math.log10(account) + 1), (int) (Math.log10(acc.getAccountNumber()) + 1));
        assertEquals(cr, acc.getCreated());
        assertTrue(Balance == acc.getBalance());
    }

    @Test
    public void testToString() {
        String name = "name";
        double balance = 1500;
        String expected =  name + ": " + balance;
        Account acc = new Account(name, balance);
        assertTrue(expected.equals(acc.toString()));
    }

    @Test
    public void testCloseAccount(){
        Customer bhagy = new Customer("bhagy", "Password1!", "coffee");
        bhagy.addAccount(new Account("Main", 1000.0));
        Account account = bhagy.findAccount("Main");
        String accountName = account.getName();
        if (!bhagy.getAccounts().isEmpty()) {
            bhagy.closeAccount(accountName);
        }
        assertTrue(bhagy.getAccounts().isEmpty());

    }



    @Test

    public void testDuplicateAccountNumbers(){

        ArrayList<Integer> accountNoList = new ArrayList<Integer>();

        for(int i=0; i<10000; i++){
            int accountNo = ThreadLocalRandom.current().nextInt(1, 1000);
              while (accountNoList.contains(accountNo)) {
                accountNo = ThreadLocalRandom.current().nextInt(1, 1000);
                accountNoList.add(accountNo);
            }
        }
        Set<Integer> set = new HashSet<Integer>(accountNoList);

        assertEquals(accountNoList.size(),set.size());

    }



}
