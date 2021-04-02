package newbank.server;

import java.time.LocalDate;
import java.util.Random;

public class Loan {
    private String loanID;
    private CustomerID lenderID;
    private CustomerID borrowerID;
    private double loanAmount;
    private double repaymentAmount;
    private double outstandingAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String paymentFrequency;
    private int loanMonths;
    private int paymentsMade;
    private int paymentsRemaining;
    private double interestRate;

    public Loan(double loanAmount, LocalDate startDate, LocalDate endDate,
                String paymentFrequency, int repaymentAmount, int loanMonths,
                double interestRate, CustomerID lenderID, CustomerID borrowerID){
        this.loanAmount = loanAmount;
        this.startDate = startDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
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

}
