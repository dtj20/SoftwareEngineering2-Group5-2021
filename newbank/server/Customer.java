package newbank.server;

import java.time.LocalDate;
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

	private final ArrayList<Loan> activeLenderLoan = new ArrayList<>();
	private final ArrayList<Loan> activeBorrowerLoan = new ArrayList<>();
	private final ArrayList<Loan> finishedLenderLoan = new ArrayList<>();
	private final ArrayList<Loan> finishedBorrowerLoan = new ArrayList<>();

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

	public boolean closeAccount(String accountName) {
		Account acc = findAccount(accountName);
		if(acc!=null){
			int index = accounts.indexOf(acc);
			accounts.remove(index);
			return true;
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
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()???[{}]:;',?/*~$^+=<>]).{8,20}$";

	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	public static boolean isValid(final String password) {
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean changePassword(CustomerID customer, String password){
		if(isValid(password)){
			this.password = password;
			return true;
		} else {
			return false;
		}
	}

	//FUNCTIONS TO DEAL WITH ACTIVE-COMPLETED LOANS

	//get - add - remove active loans (as lender)
	public ArrayList getActiveLenderLoan(){
		return activeLenderLoan;
	}
	public void addActiveLenderLoan(Loan loan){
		activeLenderLoan.add(loan);
	}
	public void removeActiveLenderLoan(Loan loan){
		activeLenderLoan.remove(loan);
	}

	//get - add - remove active loans (as borrower)
	public ArrayList getActiveBorrowerLoan(){
		return activeBorrowerLoan;
	}
	public void addActiveBorrowerLoan(Loan loan){
		activeBorrowerLoan.add(loan);
		double loanAmount = loan.getLoanAmount();
		findAccount("Main").addFunds(loanAmount);
	}
	public void removeActiveBorrowerLoan(Loan loan){
		activeBorrowerLoan.remove(loan);
	}

	//get - add - remove completed loans (as lender)
	public ArrayList getFinishedLenderLoan(){
		return finishedLenderLoan;
	}
	public void addFinishedLenderLoan(Loan loan){
		finishedLenderLoan.add(loan);
	}
	public void removeFinishedLenderLoan(Loan loan){
		finishedLenderLoan.remove(loan);
	}

	//get - add - remove completed loans (as borrower)
	public ArrayList getFinishedBorrowerLoan(){
		return finishedBorrowerLoan;
	}
	public void addFinishedBorrowerLoan(Loan loan){
		finishedBorrowerLoan.add(loan);
	}
	public void removeFinishedBorrowerLoan(Loan loan){
		finishedBorrowerLoan.remove(loan);
	}

}



