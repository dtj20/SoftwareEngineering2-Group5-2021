package newbank.server;

public class Account {
	
	private String accountName;
	private double openingBalance;
	public double balance;

	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
		this.balance = openingBalance;
	}
	
	public String toString() {
		return (accountName + ": " + balance);
	}

	public String getName(){
		return accountName;
	}

	public double getBalance() {
		return balance;
	}

}
