package newbank.server;

public class Account {
	
	private String accountName;
	public double balance;

	public Account(String accountName, double balance) {
		this.accountName = accountName;
		this.balance = balance;
	}
	
	public String toString() {
		return (accountName + ": " + balance);
	}

	public String getName(){
		return accountName;
	}

}
