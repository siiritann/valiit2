package ee.bcs.valiit.tasks.BankController;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Account {
    private String accNo;
    private BigDecimal balance;


    // contructor 1
//    public Account(String accNo, BigDecimal balance) {
//        this.accNo = accNo;
//        this.balance = balance;
//        this.balance = balance;
//    }

    // constructor 2
    public Account(String accNo) {
        this.accNo = accNo;
        this.balance = BigDecimal.ZERO;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
