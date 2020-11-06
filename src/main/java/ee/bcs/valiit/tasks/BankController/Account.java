package ee.bcs.valiit.tasks.BankController;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Account {
    private BigDecimal accNo;
    private BigDecimal balance;

    public Account(BigDecimal accNo, BigDecimal balance) {
        this.accNo = accNo;
        this.balance = balance;
    }
}
