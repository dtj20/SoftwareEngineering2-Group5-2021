package newbank.server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

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
		Customer bhagy = new Customer("123");
		bhagy.addAccount(new Account("Main", 1000.0));
		customers.put("Bhagy", bhagy);
		
		Customer christina = new Customer("456");
		christina.addAccount(new Account("Savings" ,1500.0));
		customers.put("Christina", christina);
		
		Customer john = new Customer("789");
		john.addAccount(new Account("Checking", 250.0));
		customers.put("John", john);
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
					return move(amount, payerAccount, payeeAccount);
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

	private String move(double amount, String sourceAccount, String targetAccount) {
		return "";
	}

}
