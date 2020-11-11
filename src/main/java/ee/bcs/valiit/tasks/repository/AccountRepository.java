package ee.bcs.valiit.tasks.repository;


import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.AccountRowMapper;
import ee.bcs.valiit.tasks.BankController.BalanceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // Create new account with balance WITH SQL
    public void createAccount(String acc_no, BigInteger balance){
        String sql = "INSERT INTO account (acc_no, balance) VALUES (:accNo, :balance)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        paramMap.put("balance", balance);
        jdbcTemplate.update(sql, paramMap);
    }

    // Get list of accounts WITH SQL
    public Collection<Account> getAccounts() {
        String sql = "SELECT * FROM account";
        Map<String, Object> paramMap = new HashMap();
        List<Account> resultList = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
        return resultList;
    }

    // Create new account without balance WITH SQL
    public void createAccountWithoutBalance(@RequestParam("accNo") String acc_no) {
        String sql = "INSERT INTO account (acc_no, balance) VALUES (:accNo, 0)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        jdbcTemplate.update(sql, paramMap);
    }


    public BigDecimal getBalance(String accNo){
        String sql = "SELECT balance FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class); // see rida annab mulle vastuse andmebaasist
        return balance;
    }

    public void updateBalance(String accNo, BigDecimal newValue){
        String sql = "UPDATE account SET balance = :balance WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        paramMap.put("balance", newValue);
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Account> getMultipleBalances(String accNo) {
        String sql = "SELECT * FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accNo", accNo);
        List<Account> resultList = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
        return resultList;
    }

    public void updateBalanceHistory(String fromAccNo, String toAccNo, BigDecimal amount, String type){
       String sql = "INSERT INTO balance_history (from_accno, to_accno, amount, type) VALUES (:fromAccNo, :toAccNo, :amount, :type)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromAccNo", fromAccNo);
        paramMap.put("toAccNo", toAccNo);
        paramMap.put("amount", amount);
        paramMap.put("type", type);
        jdbcTemplate.update(sql, paramMap);
    }

    // POOLELI - see on sarnane nagu getMultipleBalances
//    public List<BalanceHistory> getHistory(String accNo){
//        String sql = "SELECT * FROM balance_history WHERE acc_no = :accNo";
//        Map<String, Object> paramMap = new HashMap();
//        paramMap.put("accNo", accNo);
//        List<BalanceHistory> resultList = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
//        return resultList;
//    }




/*    // depositMoney (accNo, money) WITH SQL - POOLELI
    public String depositMoney(@RequestParam("accNo") String accNo,
                               @RequestParam("money") BigDecimal money) {



        // PART 3 - update balance history
        String sqlAdd = "INSERT INTO balance_history (acc_no, amount) VALUES (:accNo, :amount)";
        Map<String, Object> historyMap = new HashMap<>();
        historyMap.put("accNo", accNo);
        historyMap.put("amount", money);
        jdbcTemplate.update(sqlAdd, historyMap);

    }*/


    // withdrawMoney (accNo, money) WITH SQL
//    public String withdrawMoney(@RequestParam("accNo") String accNo,
//                                @RequestParam("money") BigDecimal money) {

        // PART 2 - update balance



        // PART 3 - update balance history - POOLELI
 /*       String sqlAdd = "INSERT INTO balance_history (acc_no, amount) VALUES (:accNo, :amount)";
        Map<String, Object> historyMap = new HashMap<>();
        BigDecimal updatedMoney = money.multiply(BigDecimal.ONE); // TODO PANE SIIA MIINUS
        historyMap.put("accNo", accNo);
        historyMap.put("amount", updatedMoney);
        jdbcTemplate.update(sqlAdd, historyMap);
*/
//    }
}
