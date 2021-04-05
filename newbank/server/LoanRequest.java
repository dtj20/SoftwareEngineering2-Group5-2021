package newbank.server;

import java.util.Date;

public class LoanRequest {

    //update to CustomerID
    private CustomerID borrowerID;
    private long loanRequestAmount;
    private Date requestedMaturityDate;
    private int requestedInterestRate;
    private int loanRequestId = 0;

    public LoanRequest(CustomerID borrowerID, long loanRequestAmount, Date requestedMaturityDate, int requestedInterestRate) {
        this.borrowerID = borrowerID;
        this.loanRequestAmount = loanRequestAmount;
        this.requestedMaturityDate = requestedMaturityDate;
        this.requestedInterestRate = requestedInterestRate;
        this.loanRequestId = loanRequestId++;
    }

    public CustomerID getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(CustomerID borrowerID) {
        this.borrowerID = borrowerID;
    }

    public long getLoanRequestAmount() {
        return loanRequestAmount;
    }

    public void setLoanRequestAmount(long loanRequestAmount) {
        this.loanRequestAmount = loanRequestAmount;
    }

    public Date getRequestedMaturityDate() {
        return requestedMaturityDate;
    }

    public void setRequestedMaturityDate(Date requestedMaturityDate) {
        this.requestedMaturityDate = requestedMaturityDate;
    }

    public int getRequestedInterestRate() {
        return requestedInterestRate;
    }

    public void setRequestedInterestRate(int requestedInterestRate) {
        this.requestedInterestRate = requestedInterestRate;
    }

    public int getLoanRequestId() {
        return loanRequestId;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "borrowerID='" + borrowerID + '\'' +
                ", loanRequestAmount=" + loanRequestAmount +
                ", requestedMaturityDate=" + requestedMaturityDate +
                ", requestedInterestRate=" + requestedInterestRate +
                ", loanRequestId=" + loanRequestId +
                '}';
    }
}
