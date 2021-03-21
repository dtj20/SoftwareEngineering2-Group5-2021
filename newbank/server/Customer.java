package newbank.server;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

	public boolean addAccount(Account account) {
		try {
		    accounts.add(account);
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

	//Check the username is *not* case sensitive

	private static final String USERNAME_PATTERN =
			"^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,25}[a-zA-Z0-9]$";

	private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);

	public static boolean caseInsensitiveUsername(final String username) {
		Matcher caseUsername = usernamePattern.matcher(username);
		return caseUsername.matches();
	}

	//alternative code version

	/*
	private static final Pattern usernamePattern = Pattern.compile(username, Pattern.CASE_INSENSITIVE);

	public static boolean caseInsensitiveUsername(final String username) {
		Matcher match = usernamePattern.matcher(username);
	}
	*/

}