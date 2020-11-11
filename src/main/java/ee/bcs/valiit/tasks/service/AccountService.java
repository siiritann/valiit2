package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.AccountRowMapper;
import ee.bcs.valiit.tasks.BankController.BalanceHistory;
import ee.bcs.valiit.tasks.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate; // TÖÖTAB

    // Create new account with balance WITH SQL
    public void createAccount(String acc_no, BigInteger balance){
        accountRepository.createAccount(acc_no, balance);
    }

    // Get list of accounts WITH SQL
    public Collection<Account> getAccounts() {
        return accountRepository.getAccounts();
    }

    // Create new account without balance WITH SQL
    public void createAccountWithoutBalance(@RequestParam("accNo") String acc_no) {
        accountRepository.createAccountWithoutBalance(acc_no);
    }

    // depositMoney (accNo, money) WITH SQL
    public String depositMoney(@RequestParam("accNo") String accNo,
                               @RequestParam("money") BigDecimal money) {
        BigDecimal accountBalanceOldValue = accountRepository.getBalance(accNo);
        BigDecimal newValue = accountBalanceOldValue.add(money.abs());
        accountRepository.updateBalance(accNo, newValue);

        accountRepository.updateBalanceHistory(accNo, "", money.abs(),"deposit");

        return "previous amount was " + accountBalanceOldValue + ", new amount is " + newValue;
    }


    // withdrawMoney (accNo, money) WITH SQL
    public String withdrawMoney(@RequestParam("accNo") String accNo,
                                @RequestParam("money") BigDecimal money) {
        BigDecimal accountBalanceOldValue = accountRepository.getBalance(accNo);
        if (money.abs().compareTo(accountBalanceOldValue) <= 0) {
            BigDecimal newValue = accountBalanceOldValue.subtract(money.abs());
            accountRepository.updateBalance(accNo, newValue);
            BigDecimal minus = new BigDecimal("-1");
            accountRepository.updateBalanceHistory(accNo, "", money.abs().multiply(minus),"withdraw");
            return "previous amount was " + accountBalanceOldValue + ", new amount is " + newValue;
        } else {
            return "Not enough money";
        }

        // PART 3 - update balance history - POOLELI
 /*       String sqlAdd = "INSERT INTO balance_history (acc_no, amount) VALUES (:accNo, :amount)";
        Map<String, Object> historyMap = new HashMap<>();
        BigDecimal updatedMoney = money.multiply(BigDecimal.ONE); // TODO PANE SIIA MIINUS
        historyMap.put("accNo", accNo);
        historyMap.put("amount", updatedMoney);
        jdbcTemplate.update(sqlAdd, historyMap);
*/
    }

    // transferMoney WITH SQL
    public String transferMoney(@RequestParam("fromAccount") String accNo1,
                                @RequestParam("toAccount") String accNo2,
                                @RequestParam("money") BigDecimal money) {

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
            accountRepository.updateBalanceHistory(accNo1, accNo2, money.abs().multiply(minus),"withdraw");

            // to Account update
            BigDecimal newValueToAccount = oldValueToAccount.add(money.abs());
            accountRepository.updateBalance(accNo2, newValueToAccount);
            accountRepository.updateBalanceHistory("", accNo2, money.abs(),"deposit");

            return "ACCOUNT 1: previous amount was " + oldValueFromAccount + ", new amount is " + newValueFromAccount + "\n" + "ACCOUNT 2: previous amount was " + oldValueToAccount + ", new amount is " + newValueToAccount;
        } else {
            return "Not enough money";
        }
    }



    // getAccountBalance (accountNr) WITH SQL
    public BigDecimal getAccountBalance(@PathVariable("accNo") String accNo) {
        return accountRepository.getBalance(accNo);
    }

    // example for multiple rows WITH SQL
    // kutsutakse iga rea kohta välja
    // returnib mulle muutuja, mis on list nendest samadest objektidest mida row mapperis defineerisime
    public List<Account> selectManyRowsSample(@PathVariable("accNo") String accNo) {
        return accountRepository.getMultipleBalances(accNo);
    }

//    public List<BalanceHistory> getHistory(String accNo) {
//        return accountRepository.getHistory(accNo);
//    }

/*    public void insertBalanceHistory(String accNo, BigDecimal amount){
        accountRepository.updateBalanceHistory(accNo, amount);
    }*/

//      public void getBalanceHistory(accountNr)
//        @GetMapping("account/{accNo}")
//        public void getBalanceHistory (@PathVariable("accId") String accNo){
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("accNo", accNo);
//            BigDecimal oldValueFromAccount = jdbcTemplate.queryForObject(sqlGet1, paramMap, BigDecimal.class);
//            return;
//        }



}
