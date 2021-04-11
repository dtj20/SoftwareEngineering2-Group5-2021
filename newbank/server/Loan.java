package newbank.server;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Loan {
    private String loanID;
    private CustomerID lenderID;
    private CustomerID borrowerID;
    private double loanAmount;
    private double repaymentAmount;
    private double outstandingAmount;
    private Date startDate;
    private Date endDate;
    private String paymentFrequency;
    private int loanMonths;
    private int paymentsMade;
    private int paymentsRemaining;
    private double interestRate;

    public Loan(double loanAmount, Date startDate, Date endDate,
                String paymentFrequency, int repaymentAmount, int loanMonths,
                double interestRate, CustomerID lenderID, CustomerID borrowerID){
        this.loanAmount = loanAmount;
        this.startDate = new Date();
        this.endDate = endDate;
        this.paymentFrequency = paymentFrequency;
        this.loanMonths = loanMonths;
        this.interestRate = interestRate;
        this.repaymentAmount = repaymentAmount;
        this.outstandingAmount = loanAmount;
        this.paymentsMade = 0;
        this.paymentsRemaining = loanMonths;
        this.loanID = createLoanID();
        this.lenderID = lenderID;
        this.borrowerID = borrowerID;


    }

    public String createLoanID() {
        String LoanID = "Loan" + lenderID + borrowerID + getRandomCode();
        return LoanID;
    }


    public int getRandomCode() {
        Random r = new Random();
        int x = r.nextInt(100);
        return x;
    }


    public String getLoanID() {
        return loanID;
    }


    public double getLoanAmount() {
        return loanAmount;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }

    public double getOutstandingAmount() {
        return outstandingAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public int getLoanMonths() {
        return loanMonths;
    }

    public int getPaymentsRemaining() {
        return paymentsRemaining;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setLenderID(CustomerID lenderID) {
        this.lenderID = lenderID;
    }

    public void setBorrowerID(CustomerID borrowerID) {
        this.borrowerID = borrowerID;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setRepaymentAmount(double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public void setOutstandingAmount(double outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public void setLoanMonths(int loanMonths) {
        this.loanMonths = loanMonths;
    }

    public void setPaymentsMade(int paymentsMade) {
        this.paymentsMade = paymentsMade;
    }

    public void setPaymentsRemaining(int paymentsRemaining) {
        this.paymentsRemaining = paymentsRemaining;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
