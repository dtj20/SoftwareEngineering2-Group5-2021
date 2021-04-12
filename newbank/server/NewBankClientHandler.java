package newbank.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;

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
			while(!loginSuccess) {
				out.println("Would you like to LOGIN or REGISTER?\nForgot your password? enter P.");
				String userChoice = in.readLine().toLowerCase();
				if (userChoice.equals("register")) {
					if (bank.newCustomer()) {
					}
					out.println("SUCCESS! We've created you a main account and added £5 as a thank you for choosing New Bank." +
							" Login to access your account");
				} else if (userChoice.equals("login")) {
					// ask for user name
					out.println("Enter Username"); //OMAR: method should authenticate username first and then password
					String username = in.readLine().toLowerCase();
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
						boolean run_loop = true;
						while (run_loop) {
						 out.println("Enter a number to choose from the following options. \n1: Show my accounts. " +
							"\n2: Create a new savings account. \n3: Create a new checking account. " +
									"\n4: Move money between your accounts \n5: Pay another person\n6: Change password\n7: Close account\n8: Help. \n9: Logout\n");
						 out.println("-----LOANS-----\nTo view loans please enter: loans\nTo apply for a loan enter: apply\nTo offer a loan enter: offer");
							String request = in.readLine();
							if (request.equals("9")) {
								run_loop = false;
								loginSuccess = false;
							} else {
								System.out.println("Request from " + customer.getKey());
								String response = bank.processRequest(customer, request); //was responce, changed to response
								out.println(response);
								loginSuccess = true;
								out.println("Press Enter to continue or 9 to logout");

								if (in.readLine().equals("9")) {
									run_loop = false;
									loginSuccess = false;
								}
							}
						}
					} else {
						out.println("Log In Failed");
					}
				} else if(userChoice.equals("p")) {
					out.println("Enter Username:");
					String username = in.readLine().toLowerCase();

					out.println("Enter the 1st character from your memorable word:");
					String firstChar = in.readLine();

					out.println("Enter the 3rd character from your memorable word:");
					String secondChar = in.readLine();

					out.println("Enter the 6th character from your memorable word:");
					String thirdChar = in.readLine();

					String threeChars = firstChar + secondChar + thirdChar;

					if(bank.checkMemorableWord(username, threeChars)){

						out.println("Please enter your strong new password:");
						String newPassword = in.readLine();

						if(Customer.isValid(newPassword)) {
							bank.updatePassword(username, newPassword);
							out.println("You've successfully updated your password.");
						}

					} else {
						out.println("Your character selection does not match.");
					}

				}

			}
		} catch (IOException | ParseException e) {
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
