package newbank.server;

import java.util.ArrayList;

public class DirectDebit {

    private int directDebitID = 0;
    private String accountReceiver;
    private double amount;
    private String paymentFrequency;
  


    public DirectDebit(String accountReceiver, double amount, String paymentFrequency) {
        this.directDebitID = directDebitID++;
        this.accountReceiver = accountReceiver;
        this.amount = amount;
        this.paymentFrequency = paymentFrequency;
    }

    public int getDirectDebitID() {
        return directDebitID;
    }

    public String getAccountReceiver() {
        return accountReceiver;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setAccountReceiver(String accountReceiver) {
        this.accountReceiver = accountReceiver;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    @Override
    public String toString() {
        return "DirectDebit{" +
                "DirectDebitID='" + directDebitID + '\'' +
                ", Account Receiver=" + accountReceiver +
                ", Amount=" + amount +
                ", Payment Frequency=" + paymentFrequency +
                '}';
    }
}





