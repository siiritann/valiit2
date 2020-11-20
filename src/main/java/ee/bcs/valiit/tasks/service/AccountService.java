/* TODO - withdraw ja transfer - lisa account exists control

 *
 * */


package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.BalanceHistory;
import ee.bcs.valiit.tasks.BankController.TransactionHistory;
import ee.bcs.valiit.tasks.exception.ApplicationException;
import ee.bcs.valiit.tasks.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // Create new account with balance WITH SQL
    public int createAccount(String accNo, BigDecimal balance, int clientId) {

        // check if this accNo already exists in DB
        if (accountRepository.ifAccDoesExist(accNo)) {
            throw new ApplicationException("This account already exists");
        }

        // Create new account
        int idOfAccount = accountRepository.createAccount(accNo, balance, clientId);
        //int idOfAccount = accountRepository.getAccountId(accNo);
        accountRepository.putTransactionHistory(null, idOfAccount, balance.abs(), "openAccountDeposit");
        return idOfAccount;
    }

    // Get list of accounts WITH SQL
    public Collection<Account> getAccounts() {
        return accountRepository.getAccounts();
    }

    // Create new account without balance WITH SQL
    public void createAccountWithoutBalance(String acc_no, int clientId) {
        accountRepository.createAccountWithoutBalance(acc_no, clientId);
    }

    // depositMoney (accNo, money) WITH SQL
    public String depositMoney(String accNo, BigDecimal money) {

        // check if this accNo exists in DB
        if (!accountRepository.ifAccDoesExist(accNo)) {
            throw new ApplicationException("Account doesn't exist", 418);
        }

        // if account exists then add to balance
        BigDecimal accountBalanceOldValue = accountRepository.getBalance(accNo);
        BigDecimal newValue = accountBalanceOldValue.add(money.abs());
//        accountRepository.updateBalanceHistory( "", accNo, money.abs(),"deposit"); // DEPRECATED
        accountRepository.updateBalance(accNo, newValue);


        // update transaction History
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
            throw new ApplicationException("Not enough money", 400);
//            return "Not enough money"; // DEPRECATED

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

    // GET ACCOUNTS FOR THIS CLIENT
    public Collection<Account> getAccountsForThisClient(int clientId){
        return accountRepository.getAccountsForThisClient(clientId);
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

    public List<TransactionHistory> getHistoryOfAccount(String accNo) {
        int idOfAccount = accountRepository.getAccountId(accNo);
        return accountRepository.getHistoryOfAccount(idOfAccount);
    }


    // get balances for all accounts
    @GetMapping("balances")
    public List<Object> getBalances(){
        return accountRepository.getBalancesForAccounts();

    }
}
