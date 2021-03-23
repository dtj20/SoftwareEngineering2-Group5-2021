package newbank.server;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

	private String customerName;
	private ArrayList<Account> accounts;
    private String password;
	private String memorableWord;
	private static List<Customer> allCustomers;

	public Customer(String accountName, String password, String memorableWord) {
		this.customerName = accountName;
		this.password = password;
		this.memorableWord =  memorableWord;
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

	public String getMemorableWord() {
		return memorableWord;
	}

	public void setMemorableWord(String memorableWord) {
		this.memorableWord = memorableWord;
	}

	public boolean addAccount(Account account) {
		try {
		    accounts.add(account);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	 public boolean closeAccount(int accountNumber) {
		 for (Account account : accounts) {
			 if (account.getAccountNumber() == accountNumber) {
				 int index = accounts.indexOf(account);
				 accounts.remove(index);
				 return true;
			 }
		 }
		 return false;
	 }

	/*
	 * Method to check whether a customerName already exists
	 */
	public static boolean isCustomer(String customerName) {

		return Customer.allCustomers.stream().anyMatch(customer -> customer.customerName.equals(customerName));
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

	/*
	 * Method to find an account based on the accountNumber provided.
	 */
	public Account findAccountByAccountNumber(int accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber() == accountNumber) {
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

	/*
	 * Secure Password regex for the requirements below:
       Password must contain at least one digit [0-9].
                             at least one lowercase Latin character [a-z].
                             at least one uppercase Latin character [A-Z].
                             at least one special character like ! @ # & ( ).
                             a length of at least 8 characters and a maximum of 20 characters.
	 */
	private static final String PASSWORD_PATTERN =
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	public static boolean isValid(final String password) {
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public Boolean changePassword(CustomerID customer, String password){
		if(isValid(password)){
			this.password = password;
			return true;
		} else {
			return false;
		}
	}

}
