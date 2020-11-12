package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.BalanceHistory;
import ee.bcs.valiit.tasks.BankController.TransactionHistory;
import ee.bcs.valiit.tasks.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate; // TÖÖTAB

    // Create new account with balance WITH SQL
    public void createAccount(String accNo, BigDecimal balance, int clientId){
        accountRepository.createAccount(accNo, balance, clientId);
        int idOfAccount = accountRepository.getAccountId(accNo);
        accountRepository.putTransactionHistory(null, idOfAccount, balance.abs(),"openAccountDeposit");
    }

    // Get list of accounts WITH SQL
    public Collection<Account> getAccounts() {
        return accountRepository.getAccounts();
    }

    // Create new account without balance WITH SQL
    public void createAccountWithoutBalance(String acc_no, int clientId) {
        accountRepository.createAccountWithoutBalance(acc_no, clientId);
    }

    // depositMoney (accNo, money) WITH SQL - POOLELI
    public String depositMoney(String accNo, BigDecimal money) {
        BigDecimal accountBalanceOldValue = accountRepository.getBalance(accNo);
        BigDecimal newValue = accountBalanceOldValue.add(money.abs());
        accountRepository.updateBalance(accNo, newValue);

//        accountRepository.updateBalanceHistory( "", accNo, money.abs(),"deposit");

        int idOfAccount = accountRepository.getAccountId(accNo);
        accountRepository.putTransactionHistory(null, idOfAccount, money.abs(),"deposit");

        return "previous amount was " + accountBalanceOldValue + ", new amount is " + newValue;
    }


    // withdrawMoney (accNo, money) WITH SQL
    public String withdrawMoney(String accNo, BigDecimal money) {
        BigDecimal accountBalanceOldValue = accountRepository.getBalance(accNo);
        if (money.abs().compareTo(accountBalanceOldValue) <= 0) {
            BigDecimal newValue = accountBalanceOldValue.subtract(money.abs());
            accountRepository.updateBalance(accNo, newValue);
            BigDecimal minus = new BigDecimal("-1");
//            accountRepository.updateBalanceHistory(accNo, "", money.abs().multiply(minus),"withdraw"); // DEPRECATED

            int idOfAccount = accountRepository.getAccountId(accNo);
            accountRepository.putTransactionHistory(idOfAccount, null, money.abs().multiply(minus),"withdraw");

            return "previous amount was " + accountBalanceOldValue + ", new amount is " + newValue;
        } else {
            return "Not enough money";
        }
    }

    // transferMoney WITH SQL
    public String transferMoney(String accNo1, String accNo2, BigDecimal money) {

        // PART 1 - get current balance for both
        BigDecimal oldValueFromAccount = accountRepository.getBalance(accNo1);
        BigDecimal oldValueToAccount = accountRepository.getBalance(accNo2);

        // PART 2 - check if possible to make transfer

        if (money.abs().compareTo(oldValueFromAccount) <= 0) {

            // PART 3 - update balance

            // from Account update
            BigDecimal newValueFromAccount = oldValueFromAccount.subtract(money.abs());
            accountRepository.updateBalance(accNo1, newValueFromAccount);
            BigDecimal minus = new BigDecimal("-1");
//            accountRepository.updateBalanceHistory(accNo1, accNo2, money.abs().multiply(minus),"withdraw"); // DEPRECATED


            // to Account update
            BigDecimal newValueToAccount = oldValueToAccount.add(money.abs());
            accountRepository.updateBalance(accNo2, newValueToAccount);
//            accountRepository.updateBalanceHistory(accNo1, accNo2, money.abs(),"deposit"); // DEPRECATED


            // update transactionHistory
            int idOfFromAccount = accountRepository.getAccountId(accNo1);
            int idOfToAccount = accountRepository.getAccountId(accNo2);
            accountRepository.putTransactionHistory(idOfFromAccount, idOfToAccount, money.abs().multiply(minus),"withdraw");
            accountRepository.putTransactionHistory(idOfFromAccount, idOfToAccount, money.abs(),"deposit");

            return "ACCOUNT 1: previous amount was " + oldValueFromAccount + ", new amount is " + newValueFromAccount + "\n" + "ACCOUNT 2: previous amount was " + oldValueToAccount + ", new amount is " + newValueToAccount;
        } else {
            return "Not enough money";
        }
    }



    // getAccountBalance (accountNr) WITH SQL
    public BigDecimal getAccountBalance(String accNo) {
        return accountRepository.getBalance(accNo);
    }

    // example for multiple rows WITH SQL
    // kutsutakse iga rea kohta välja
    // returnib mulle muutuja, mis on list nendest samadest objektidest mida row mapperis defineerisime
    public List<Account> selectManyRowsSample(String accNo) {
        return accountRepository.getMultipleBalances(accNo);
    }

    public List<BalanceHistory> getHistory(String accNo) {
        return accountRepository.getHistory(accNo);
    }

    // POOLELI
    public List<TransactionHistory> getHistoryOfAccount(String accNo) {
        int idOfAccount = accountRepository.getAccountId(accNo);
        return accountRepository.getHistoryOfAccount(idOfAccount);
    }
}
