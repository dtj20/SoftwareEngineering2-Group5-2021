package newbank.server;

import java.util.ArrayList;

public class DirectDebit {

    private String directDebitID;
    private String accountReceiver;
    private double amount;
    private String paymentFrequency;
  


    public DirectDebit(String directDebitID, String accountReceiver, double amount, String paymentFrequency) {
        this.directDebitID = directDebitID;
        this.accountReceiver = accountReceiver;
        this.amount = amount;
        this.paymentFrequency = paymentFrequency;
    }

    public String getDirectDebitID() {
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

    public void setDirectDebitID(String directDebitID) {
        this.directDebitID = directDebitID;
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


}





