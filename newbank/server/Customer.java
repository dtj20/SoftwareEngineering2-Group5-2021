package newbank.server;

import java.util.ArrayList;

public class Customer {
	
	private ArrayList<Account> accounts;
	private String password;

	public Customer(String password) {
		this.password = password;
		accounts = new ArrayList<>();
	}

	public String accountsToString() {
		String s = "";

		for(Account a : accounts) {
			s += a.toString();
		}
		return s;
	}

	public ArrayList<Account> getAccounts(){return accounts;}
	public String getPassword(){return password;}


	public void addAccount(Account account) {
		accounts.add(account);		
	}
}
