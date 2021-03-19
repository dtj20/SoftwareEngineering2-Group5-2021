package newbank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewBankClientHandler extends Thread{

	private NewBank bank;
	private BufferedReader in;
	private PrintWriter out;
    public String[] loginArray;
    public boolean loginSuccess = false;


	public NewBankClientHandler(Socket s) throws IOException {
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(s.getOutputStream(), true);
		bank = NewBank.getBank(in, out);
	}

	public void run() {
		// keep getting requests from the client and processing them
		try {
			while(loginSuccess) {
				// ask for user name
				out.println("Enter Username"); //OMAR: method should authenticate username first and then password
				String username = in.readLine();
				//loginArray[0] = userName;
				// ask for password
				out.println("Enter Password");
				String password = in.readLine();
				//loginArray[1] = password;
				out.println("Checking Details...");
				// authenticate user and get customer ID token from bank for use in subsequent requests
				CustomerID customer = bank.checkLogInDetails(username, password);

				// if the user is authenticated then get requests from the user and process them
				if (customer != null) {
					out.println("Log In Successful.");
					boolean loop = true;
					while (loop) {
						out.println("Enter a number to choose from the following options. \n1: Show My accounts. " +
								"\n2: create a new savings account. \n3: create a new checking account. " +
								"\n4: move money between your accounts \n5: Pay another person\n8: help. \n9: logout\n");
						String request = in.readLine();
						if(request.equals("9")) {
							loop = false;
						} else {
							System.out.println("Request from " + customer.getKey());
							String response = bank.processRequest(customer, request); //was responce, changed to response
							out.println(response);
							loginSuccess = true;
							out.println("Press Enter to continue or 9 to logout");

							if(in.readLine().equals("9")) {
								loop = false;
							}
						}
					}
				} else {
					out.println("Log In Failed");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				System.out.println("x");
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

}
