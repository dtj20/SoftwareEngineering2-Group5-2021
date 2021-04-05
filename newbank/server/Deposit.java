package newbank.server;

import java.util.Random;
import java.util.Date;

public class Deposit {

    private String depositID;
    private long depositDate;
    private Date date;
    private String payingAccount;
    private String payingSort;
    private String recieveingAccount;
    private double amount;
    private Source source;

    public Deposit(String payingAccount, String payingSort, String recieveingAccount, double amount, Source source) {
        this.payingAccount = payingAccount;
        this.payingSort = payingSort;
        this.recieveingAccount = recieveingAccount;
        this.amount = amount;
        this.depositID = createDepositID();
        this.depositDate = createDepositDate();
        this.source = source;
    }

    private String createDepositID() {
        String deposit = "D"+ this.getRecieveingAccount() + "-" + this.getDepositDate() + getRandomCode();
        return deposit;
    }

    private int getRandomCode() {
        Random r = new Random();
        int x = r.nextInt(100);
        return x;
    }

    private long createDepositDate() {
        Date date = new Date();
        this.date = date;
        return date.getTime();
    }

    public String getDepositSummary() {
        String summary = getDepositID() + "\n"
                + "Paid on " + getDate() + "\n"
                + "Paid from Account Number: " + getPayingAccount() + "\n"
                + "at Sort Code: " + getPayingSort()
                + "Paid to: " + getRecieveingAccount();
        return summary;
    }

    public String getDepositID() {
        return depositID;
    }

    public long getDepositDate() {
        return depositDate;
    }

    public Date getDate() {
        return date;
    }

    public String getPayingAccount() {
        return payingAccount;
    }

    public String getPayingSort() {
        return payingSort;
    }

    public String getRecieveingAccount() {
        return recieveingAccount;
    }

    public double getAmount() {
        return amount;
    }

    public Source getSource() {
        return source;
    }
}
