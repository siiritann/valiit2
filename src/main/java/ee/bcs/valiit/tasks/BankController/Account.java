// Account class peaks 1-1 vastama sellele, mis on andmebaasis - samad väljad
// javas on camelcase, SQL-is on snakecase


package ee.bcs.valiit.tasks.BankController;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Account {
    private int id;
    private String accNo;
    private BigDecimal balance;
    private int clientId;

    // default constructor 1
    public Account() {
    }

    // constructor 2
    public Account(String accNo) {
        this.accNo = accNo;
        this.balance = BigDecimal.ZERO;
    }

    // constructor 3
    public Account(String accNo, BigDecimal balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
