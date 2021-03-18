package newbank.server;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String accountName;
	private ArrayList<Account> accounts;
    private String password;
	private static List<Customer> allCustomers;

	public Customer(String accountName, String password) {
		this.accountName = accountName;
		this.password = password;
		this.accounts = new ArrayList<>();
		this.allCustomers = new ArrayList<>();
	}
	
	public String accountsToString() {
		String s = "";
		for(Account a : accounts) {
			s += a.toString() + "\n";
		}
		return s;
	}

	public String addAccount(Account account) {
		try {accounts.add(account);
			return "SUCCESS";
		}
		catch(Exception e) {
			return "FAIL";
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

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
	    return password;
	}

	public Boolean changePassword(CustomerID customer, String password){
		if(password.length()>=6){
			this.password = password;
			return true;
		} else {
			return false;
		}
	}

}
