package newbank.server;

import java.util.Date;

public class LoanOffer {

    //update to CustomerID
    private CustomerID lenderID;
    private long offeredLoanAmount;
    private Date offeredMaturityDate;
    private int offeredInterestRate;
    private String paymentFrequency;
    private int loanOfferId = 0;

    public LoanOffer(CustomerID lenderID, long offeredLoanAmount, Date offeredMaturityDate, int offeredInterestRate) {
        this.lenderID = lenderID;
        this.offeredLoanAmount = offeredLoanAmount;
        this.offeredMaturityDate = offeredMaturityDate;
        this.offeredInterestRate = offeredInterestRate;
        this.loanOfferId = loanOfferId++;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public CustomerID getLenderID() {
        return lenderID;
    }

    public void setLenderID(CustomerID lenderID) {
        this.lenderID = lenderID;
    }

    public long getOfferedLoanAmount() {
        return offeredLoanAmount;
    }

    public void setOfferedLoanAmount(long offeredLoanAmount) {
        this.offeredLoanAmount = offeredLoanAmount;
    }

    public Date getOfferedMaturityDate() {
        return offeredMaturityDate;
    }

    public void setOfferedMaturityDate(Date offeredMaturityDate) {
        this.offeredMaturityDate = offeredMaturityDate;
    }

    public int getOfferedInterestRate() {
        return offeredInterestRate;
    }

    public void setOfferedInterestRate(int offeredInterestRate) {
        this.offeredInterestRate = offeredInterestRate;
    }

    public int getLoanOfferId() {
        return loanOfferId;
    }

    @Override
    public String toString() {
        return "LoanOffer{" +
                "lenderID='" + lenderID + '\'' +
                ", offeredLoanAmount=" + offeredLoanAmount +
                ", offeredMaturityDate=" + offeredMaturityDate +
                ", offeredInterestRate=" + offeredInterestRate +
                ", loanOfferId=" + loanOfferId +
                '}';
    }
}
