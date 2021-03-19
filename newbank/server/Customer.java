package newbank.server;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String accountName;
	private ArrayList<Account> accounts;
    private String password;
	private static List<Customer> allCustomers = new ArrayList<>();

	public Customer(String password) {
		this.password = password;
		accounts = new ArrayList<>();
	}
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString() + "\n";
		}
		return s;
	}

	public boolean addAccount(Account account) {
		try {accounts.add(account);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}


	/*
	 * Method to check whether a accountName already exists
	 */
	public static boolean isCustomer(String accountName) {

		return Customer.allCustomers.stream().anyMatch(customer -> customer.accountName.equals(accountName));
	}

	/*
	 * Method to find an account based on the accountName provided.
	 */
	public Account findAccount(String accountName) {
        for (Account account : accounts) {
            if (account.getName().equals(accountName)) {
                return account;
            }
        }
        return null;
    }

	public ArrayList<Account> getAccounts(){
	    return accounts;
	}

	public String getPassword(){
	    return password;
	}

}
