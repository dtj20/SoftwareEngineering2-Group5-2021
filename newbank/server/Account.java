package newbank.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Account {
	
	private String accountName;
	private double openingBalance;
	public double balance;
	private int accountNumber;
	private String created;
	private int sort = 203045;
	private String IBAN;

	public Account(String accountName, double openingBalance) {
		this.accountName = accountName;
		this.openingBalance = openingBalance;
		this.balance = openingBalance;

		this.accountNumber = ThreadLocalRandom.current().nextInt(10000000, 100000000);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date created = Calendar.getInstance().getTime();
		this.created = dateFormat.format(created);

		this.IBAN = "GB" + this.accountNumber + "1010";

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

	public int getAccountNumber() { return accountNumber; }

	public String getCreated() { return created; }

	public int getSort() { return sort; }

	public String getIBAN() { return IBAN; }

}
