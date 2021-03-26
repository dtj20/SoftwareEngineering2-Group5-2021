package newbank.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;
	private BufferedReader in;
	private PrintWriter out;
	private NewBank() {
		customers = new HashMap<>();
		addTestData();
	}
	
	private void addTestData() {
		Customer bhagy = new Customer("bhagy","123");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("bhagy", bhagy);

		Customer christina = new Customer("christina","456");
		christina.addAccount(new Account("Savings" ,1500.0));
		customers.put("christina", christina);

		Customer john = new Customer("john","789");
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
				if(customers.get(customer.getKey())
						.addAccount(new Account("Savings",0.0))) {
					return "New savings account created.\n" + showMyAccounts(customer);
				} else {
					return "Unable to create account.";
				}
			} else if (request.equals("3")) {
				if(customers.get(customer.getKey())
						.addAccount(new Account("Checking",0.0))) {
					return "New Checking account created.\n" + showMyAccounts(customer);
				} else {
					return "Unable to create account.";
				}
			} else if (request.equals("4")) {
					double amount = Double.parseDouble(menuResponseBuilder("Please enter an amount"));
					String payerAccount = menuResponseBuilder("Please enter the name of the paying account");
					String payeeAccount = menuResponseBuilder("Please enter the name of the receiving account");
					return move(customer, amount, payerAccount, payeeAccount);
			} else if(request.equals("5")) {
					String amount = menuResponseBuilder("Please specify an amount");
					String payerName = menuResponseBuilder("Please specify a paying account");
					String receivingAccount = menuResponseBuilder(("Please specify a receiving account"));
					return pay(customer, amount, payerName, receivingAccount);
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
	 * Pay method.
	 */
	private String pay(CustomerID customer, String amount, String payerName, String receiverName) {

		if (Customer.isCustomer(payerName) && Customer.isCustomer(receiverName)) {
			Customer payer = customers.get(customer.getKey());
			Customer receiver = customers.get(receiverName);

			Account payerAccount = payer.findAccount(payerName);
			Account receiverAccount = receiver.findAccount(receiverName);

			payerAccount.balance -= Double.parseDouble(amount);
			receiverAccount.balance += Double.parseDouble(amount);

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
		String accountPassword = null;
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
		Customer newCustomer = new Customer(customerName,accountPassword);

		//default is to create main account for new customer
		newAccount(newCustomer, "Main", 5.00);
		customers.put(customerName, newCustomer);
		//add logic to NewBankClient handler so that a user can either login or register as a new customer
		if (customers.containsKey(customerName)){return true;}
		else { return false;}
	}

	//create new method creating new account for an existing customer

	//Question: is this code to be re-used for option 2 (savings) and 3 (checking) as it is or to be tailored as newSavingsAccount and newCheckingAccount?

	public String newAccount(Customer customer, String accountName, Double openingBalance)
	{
		Scanner input = new Scanner(System.in);

		customer.addAccount(new Account(accountName, openingBalance));

		int totalAccounts = 3; //hard-coded as three for "bhagy", "christina" and "john"
		String depositChoice;

//		System.out.print("Would you like to create a checking or savings account? ");
//		accountType = input.next();

		//check to ensure the maximum number of accounts (10) has not been reached
		if(totalAccounts <= 10)
		{
			//get the account name and check to ensure it doesn't already exist, loop until new name
			System.out.println("Enter the name of your new account: ");
			accountName = input.next();

			while(accountName.equalsIgnoreCase(String.valueOf(customers)))
			{
				System.out.println("Account name already exists. Please re-enter a new name. ");
			}

			System.out.print("Do you want to make an initial deposit? (Yes/No): ");
			depositChoice = input.next();

			while(!(depositChoice.equals("Yes") || depositChoice.equals("No")))
			{
				System.out.println("Error. Incorrect character. Try again.");
			}

			if(depositChoice.equals("Yes"))
			{
				System.out.print("Enter your initial deposit: ");
				openingBalance = input.nextDouble();
			}

			totalAccounts++;
			return "Successfully created new account. ";
		}
		else
		{
			return "Maximum number of accounts reached."; //Question: adding option to remove accounts too?
		}
	}

}
