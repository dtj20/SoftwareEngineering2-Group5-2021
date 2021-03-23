package newbank.server;

import java.util.Random;
import java.util.Date;

public class Transaction {

    private String TransactionID;
    private long TransactionDate;
    private Date date;
    private String payingAccount;
    private String receivingAccount;
    private String receivingSort;
    private double amount;

    public Transaction(String payingAccount, String recievingAccount, String recieveingSort, double amount){
        this.payingAccount = payingAccount;
        this.receivingAccount = recievingAccount;
        this.receivingSort = recieveingSort;
        this.amount = amount;
        this.TransactionDate = createTransactionDate();
        this.TransactionID = createTransactionID();
    }

    public long createTransactionDate() {
        Date date = new Date();
        this.date = date;
        return date.getTime();
    }

    public String createTransactionID() {
        String transactionID = "TI" + this.payingAccount + "-" + this.TransactionDate + getRandomCode();
        return transactionID;
    }

    public String getTransactionID() {
        return TransactionID;
    }


    public Date getTransactionDate() {
        return date;
    }

    public String getPayingAccount() {
        return payingAccount;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public String getReceivingSort() {
        return receivingSort;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionSummary() {
        String Summary = getTransactionID() + "\n" +
                getAmount() + ": paid from " + getPayingAccount() + "\n"
                + "Paid on " + date + "\n"
                + "Paid to Account Number: " + receivingAccount + "\n"
                + "at Sort Code: " + receivingSort;
        return Summary;
    }

    public int getRandomCode() {
        Random r = new Random();
        int x = r.nextInt(100);
        return x;
    }
}
