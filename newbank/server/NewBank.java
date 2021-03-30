package newbank.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;
	private BufferedReader in;
	private PrintWriter out;

	private ArrayList<Transaction> globalTransactions = new ArrayList<>();

	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer("bhagy","123", "coffee");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("bhagy", bhagy);

		Customer christina = new Customer("christina","456", "zigzag");
		christina.addAccount(new Account("Savings" ,1500.0));
		customers.put("christina", christina);

		Customer john = new Customer("john","789", "jojoba");
		john.addAccount(new Account("Checking", 250.0));
		customers.put("john", john);
	}
	
	public static NewBank getBank(BufferedReader in, PrintWriter out) {
		bank.setOut(out);
		bank.setIn(in);
		return bank;
	}

	private void setOut(PrintWriter out) {
		this.out = out;
	}

	private void setIn(BufferedReader in) {
		this.in = in;
	}

	public synchronized CustomerID checkLogInDetails(String username, String password) {
		if(customers.containsKey(username)) {
		    if(customers.get(username).getPassword().equals(password)){
                return new CustomerID(username);
			}
		}

		return null;
	}

	// commands from the NewBank customer are processed in this method
	public synchronized String processRequest(CustomerID customer, String request) {
		if(customers.containsKey(customer.getKey())) {

			if (request.equals("1")) {
				return showMyAccounts(customer);
			} else if (request.equals("2")) {
				String accountType = "Savings";
				String accountName = menuResponseBuilder("Please enter the name of the new account");
				double openingBalance = 0;
				return newAccount(getCustomerByID(customer), accountName, openingBalance, accountType) + showMyAccounts(customer);
			} else if (request.equals("3")) {
				String accountType = "Checking";
				String accountName = menuResponseBuilder("Please enter the name of the new account");
				double openingBalance = 0;
				return newAccount(getCustomerByID(customer), accountName, openingBalance, accountType) + showMyAccounts(customer);
			} else if (request.equals("4")) {
					double amount = Double.parseDouble(menuResponseBuilder("Please enter an amount"));
					String payerAccount = menuResponseBuilder("Please enter the name of the paying account");
					String payeeAccount = menuResponseBuilder("Please enter the name of the receiving account");
					return move(customer, amount, payerAccount, payeeAccount);
			} else if(request.equals("5")) {
				String amount = menuResponseBuilder("Please specify an amount");
				String payerName = menuResponseBuilder("Please specify a paying account");
				String sortCode = menuResponseBuilder(("Please specify a sort code"));
				String payerAccountNumber = menuResponseBuilder(("Please specify a paying account number"));
				String receiverAccountNumber = menuResponseBuilder(("Please specify a receiving account number"));
				return pay(customer, amount, payerName, sortCode, payerAccountNumber, receiverAccountNumber);
			} else {
				return "Invalid Response. please choose from the menu";
			}


		}
		return "Invalid Response. Please choose from the Menu";
	}

	/**
	 * Takes a string to send to the user and returns the response
	 * @param s The string to send to the user
	 * @return the returned input from the user
	 */
	private String menuResponseBuilder(String s) {
		try {
			out.println(s);
			return in.readLine();
		} catch(Exception e) {
			return "Error";
		}
	}

	private String showMyAccounts(CustomerID customer) {
		return (customers.get(customer.getKey())).accountsToString();
	}

	/*
	 * Method for showing customer's transaction history under a specific account.
	 */
	private String showTransactionHistory(CustomerID customer, String accountName){
		try {
			Customer c = customers.get(customer.getKey());

			Account customerAccount = c.findAccount(accountName);

			return customerAccount.transactionsToString();

		} catch(Exception e) {
			return "No transactions made.";
		}
	}

	/*
	 * Pay method.
	 */
	private String pay(CustomerID customer, String amount, String payerName,
					   String sortCode, String payerAccountNumber, String receiverAccountNumber) {

		if (Customer.isCustomer(payerName)) {
			Customer payer = customers.get(customer.getKey());

			Account payerAccount = payer.findAccountByAccountNumber(Integer.parseInt(payerAccountNumber));

			payerAccount.balance -= Double.parseDouble(amount);

			if (Integer.parseInt(sortCode) == payerAccount.getSort()) {
				String receiverName = menuResponseBuilder(("Please specify a receiving account"));
				Customer receiver = customers.get(receiverName);
				Account receiverAccount = receiver.findAccountByAccountNumber(Integer.parseInt(receiverAccountNumber));
				receiverAccount.balance += Double.parseDouble(amount);
			}

			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	/*
	 * Method to change customer's password
	 */
	private String changePassword(CustomerID customer, String password) {
		if(customers.get(customer.getKey()).changePassword(customer, password)){
			return "SUCCESS. Password successfully changed.";
		} else{
			return "FAIL. Please enter a strong password that has at least one digit, " +
					"one lowercase character, " +
					"one uppercase character, " +
					"one special character " +
					"and a length of at least 8 characters and a maximum of 20 characters";
		}
	}

	//Move funds across accounts

	public String move(CustomerID customerName, double amount, String payerAccountName, String payeeAccountName)
	{
		try {

			Customer customer = customers.get(customerName.getKey());

			Account payerAccount = customer.findAccount(payerAccountName);

			Account payeeAccount = customer.findAccount(payeeAccountName);

			if (payerAccount.getBalance() >= amount) {

				payerAccount.removeFunds(amount);
				payeeAccount.addFunds(amount);

				return "Funds successfully transferred.";
			}
			else {
				return "Insufficient funds";
			}

		}
		catch(Exception e)
		{
			return "Unable to find account.";
		}
	}

	public boolean newCustomer(){
		boolean validPassword = false;
		boolean validMemWord = false;
		String accountPassword = null;
		String memorableWord = null;
		String customerName = menuResponseBuilder("Please enter the first name of the new customer").toLowerCase();
		while (!validPassword) {
			accountPassword = menuResponseBuilder("Please enter your password.\n" +
					"							Password must contain at least one digit [0-9].\n" +
					"                             at least one lowercase Latin character [a-z].\n" +
					"                             at least one uppercase Latin character [A-Z].\n" +
					"                             at least one special character like ! @ # & ( ).\n" +
					"                             a length of at least 8 characters and a maximum of 20 characters.");

			if (Customer.isValid(accountPassword)) {
                validPassword = true;
			} else{
				out.println("Password is too weak. Try something else.");
			}

		}

		while(!validMemWord) {
			memorableWord = menuResponseBuilder("Please enter a 6 character memorable word for security purposes.");

			if(memorableWord.length() == 6) {
				validMemWord = true;
			} else {
				System.out.println("Please enter a 6 character memorable word for security purposes.");
			}
		}

		Customer newCustomer = new Customer(customerName,accountPassword, memorableWord);

		//default is to create main account for new customer
		newAccount(newCustomer, "Main", 5.00, "main");
		customers.put(customerName, newCustomer);
		//add logic to NewBankClient handler so that a user can either login or register as a new customer
		if (customers.containsKey(customerName)){return true;}
		else { return false;}
	}

	//convert CustomerID object to Customer object
	private Customer getCustomerByID(CustomerID cID) {
		return (customers.get(cID.getKey()));
	}

	//create new method creating new account for an existing customer

	public String newAccount(Customer customer, String accountName, Double openingBalance, String accountType) {
		String accountNameLower = accountName.toLowerCase();
		//get the account name and check to ensure it doesn't already exist, loop until new name
		if (customer.findAccount(accountNameLower) == null) {
			customer.addAccount(new Account(accountNameLower, openingBalance));
		} else {
			while(true) {
				accountNameLower = menuResponseBuilder("Account name already exists.\nPlease re-enter a new name. ");
				if(customer.findAccount(accountNameLower) == null){
					customer.addAccount(new Account(accountNameLower, openingBalance));
					break;
				}
			}
		}
		if (accountType.equals("Savings")) {
			return "Successfully created new savings account.\n\n";
		} else {
			return "Successfully created new checking account.\n\n";
		}
	}

	public boolean checkMemorableWord(String username, String threeChar) {
		if (customers.containsKey(username)) {
			Customer customer = customers.get(username);

			String customerMatch = "";
			customerMatch += Character.toString(customer.getMemorableWord().charAt(0));
			customerMatch += Character.toString(customer.getMemorableWord().charAt(2));
			customerMatch += Character.toString(customer.getMemorableWord().charAt(5));

			return customerMatch.equals(threeChar);
		}
		return false;
	}

	public void addGlobalTransaction(Transaction t) {
		globalTransactions.add(t);

	}
	public void updatePassword(String username, String password) {
		Customer customer = customers.get(username);
		customer.setPassword(password);
	}


}
