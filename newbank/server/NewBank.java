package newbank.server;

import java.util.HashMap;

public class NewBank {
	
	private static final NewBank bank = new NewBank();
	private HashMap<String,Customer> customers;
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
	
	public static NewBank getBank() {
		return bank;
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
			switch(request) {
			case "SHOWMYACCOUNTS" : return showMyAccounts(customer);
			case "NEWACCOUNT Savings" : return customers.get(customer.getKey())
					.addAccount(new Account("Savings",0.0));
			case "NEWACCOUNT Checking" : return customers.get(customer.getKey())
					.addAccount(new Account("Checking",0.0));
			default : return "FAIL";
			}
		}
		return "FAIL";
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

}
