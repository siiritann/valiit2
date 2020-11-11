package ee.bcs.valiit.tasks.BankController;

import java.math.BigDecimal;

public class BalanceHistory {
    private int id;
    private String fromAccNo;
    private String toAccNo;
    private BigDecimal amount;
    private String transactionType; // enum on overkill

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BalanceHistory() {
    }

    public BalanceHistory(String fromAccNo, BigDecimal amount, String transactionType) {
        this.fromAccNo = fromAccNo;
        this.toAccNo = toAccNo;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public BalanceHistory(String fromAccNo, String toAccNo, BigDecimal amount, String transactionType) {
        this.fromAccNo = fromAccNo;
        this.toAccNo = toAccNo;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getFromAccNo() {
        return fromAccNo;
    }

    public void setFromAccNo(String fromAccNo) {
        this.fromAccNo = fromAccNo;
    }

    public String getToAccNo() {
        return toAccNo;
    }

    public void setToAccNo(String toAccNo) {
        this.toAccNo = toAccNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
