package ee.bcs.valiit.tasks.BankController;

import java.math.BigDecimal;

public class BalanceHistory {
    private BigDecimal BalanceHistory;
    private String type;  // enum on overkill

    public BalanceHistory(BigDecimal balanceHistory) {
        BalanceHistory = balanceHistory;
    }

    public BigDecimal getBalanceHistory() {
        return BalanceHistory;
    }

    public void setBalanceHistory(BigDecimal balanceHistory) {
        BalanceHistory = balanceHistory;
    }

    //    private enum transactionType (deposit, withDraw);
}
