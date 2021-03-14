package newbank.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Account {
	
	private String accountName;
	private double openingBalance;


	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;

	}
	
	public String toString() {
		return (accountName + ": " + openingBalance);
	}

}
