package newbank.server;

import java.util.ArrayList;

public class Customer {
	
	private ArrayList<Account> accounts;
	
	public Customer() {
		accounts = new ArrayList<>();
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
}
