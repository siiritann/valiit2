package ee.bcs.valiit.tasks.repository;


import ee.bcs.valiit.tasks.BankController.*;
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
    public void createAccount(String acc_no, BigDecimal balance, int clientId){
        String sql = "INSERT INTO account (acc_no, balance, client_id) VALUES (:accNo, :balance, :clientId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        paramMap.put("balance", balance);
        paramMap.put("clientId", clientId);
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
    public void createAccountWithoutBalance(String accNo, int clientId) {
        String sql = "INSERT INTO account (acc_no, balance, client_id) VALUES (:accNo, 0, :clientId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        paramMap.put("balance", 0);
        paramMap.put("clientId", clientId);
        jdbcTemplate.update(sql, paramMap);
    }


    public BigDecimal getBalance(String accNo){
        String sql = "SELECT balance FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class); // see rida annab mulle vastuse andmebaasist
        return balance;
    }

    public int getAccountId(String accNo){
        String sql = "SELECT id FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        int id = jdbcTemplate.queryForObject(sql, paramMap, Integer.class); // see rida annab mulle vastuse andmebaasist
        return id;
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


    public void putTransactionHistory(Integer fromAccountId, Integer toAccountId, BigDecimal amount, String type){
        String sql = "INSERT INTO transaction_history (from_account_id, to_account_id, amount, type) VALUES (:fromAccountId, :toAccountId, :amount, :type)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromAccountId", fromAccountId);
        paramMap.put("toAccountId", toAccountId);
        paramMap.put("amount", amount);
        paramMap.put("type", type);
        jdbcTemplate.update(sql, paramMap);
    }


    // POOLELI
    // get balance history, peaks olema sarnane nagu getMultipleBalances
    public List<TransactionHistory> getHistoryOfAccount(int idOfAccount){
        String sql = "SELECT * FROM transaction_history WHERE from_account_id = :idOfAccount";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("idOfAccount", idOfAccount);
        List<TransactionHistory> resultList = jdbcTemplate.query(sql, paramMap, new TransactionHistoryMapper());
        return resultList;
    }



    /* DEPRECATED
     * */
    // get balance history, peaks olema sarnane nagu getMultipleBalances
    public List<BalanceHistory> getHistory(String accNo){
        String sql = "SELECT * FROM balance_history WHERE from_accno = :accNo";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accNo", accNo);
        List<BalanceHistory> resultList = jdbcTemplate.query(sql, paramMap, new HistoryRowMapper());
        return resultList;
    }

}
