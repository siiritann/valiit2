package ee.bcs.valiit.tasks.repository;


import ee.bcs.valiit.tasks.BankController.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    // TODO POOLELI
    // Create new account with balance WITH SQL THAT RETURNS id KEY
    public Integer createAccount(String acc_no, BigDecimal balance, int clientId){
        String sql = "INSERT INTO account (acc_no, balance, client_id) VALUES (:accNo, :balance, :clientId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        paramMap.put("balance", balance);
        paramMap.put("clientId", clientId);

        // tagastab äsja loodud konto data, ise ütlen välja nt iD millelel genereeriti autom.väärtus
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    // Create new account with balance WITH SQL
  /*  public Integer createAccount(String acc_no, BigDecimal balance, int clientId){
        String sql = "INSERT INTO account (acc_no, balance, client_id) VALUES (:accNo, :balance, :clientId)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        paramMap.put("balance", balance);
        paramMap.put("clientId", clientId);
        jdbcTemplate.update(sql, paramMap);
    }*/

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


    // Get balance for one account
    public BigDecimal getBalance(String accNo){
        String sql = "SELECT balance FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class); // see rida annab mulle vastuse andmebaasist
        return balance;
    }

    // GET ACCOUNTS FOR THIS CLIENT
    public Collection<Account> getAccountsForThisClient(int clientId){
        String sql = "SELECT * FROM account where client_id = :clientId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("clientId", clientId);
        List<Account> resultList = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
        return resultList;
    }



    public int getAccountId(String accNo){
        String sql = "SELECT id FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        int id = jdbcTemplate.queryForObject(sql, paramMap, Integer.class); // see rida annab mulle vastuse andmebaasist
        return id;
    }


    // CHECK IF ACCOUNT EXISTS
    public boolean ifAccDoesExist(String accNo){
        String sql = "SELECT COUNT(*) FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        Integer count = jdbcTemplate.queryForObject(sql, paramMap, Integer.class); // see rida annab mulle vastuse andmebaasist
        return count != 0;
    }


    public void updateBalance(String accNo, BigDecimal newValue){
        String sql = "UPDATE account SET balance = :balance WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        paramMap.put("balance", newValue);
        jdbcTemplate.update(sql, paramMap);
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


    // get balance history, peaks olema sarnane nagu getMultipleBalances
    public List<TransactionHistory> getHistoryOfAccount(int idOfAccount){
        String sql = "SELECT * FROM transaction_history WHERE from_account_id = :idOfAccount";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("idOfAccount", idOfAccount);
        List<TransactionHistory> resultList = jdbcTemplate.query(sql, paramMap, new TransactionHistoryMapper());
        return resultList;
    }

    // GET balance for all accounts
    public List<Object> getBalancesForAccounts(){
        String sql = "SELECT balance FROM account";
        Map<String, Object> paramMap = new HashMap<>();
        List resultList = jdbcTemplate.queryForList(sql, paramMap, BigDecimal.class);
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

    // DEPRECATED BECAUSE WE NOW HAVE UNIQUE accNo's
    public List<Account> getMultipleBalances(String accNo) {
        String sql = "SELECT * FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("accNo", accNo);
        List<Account> resultList = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
        return resultList;
    }

    // GET BALANCE WITH ACCOUNT EXISTANCE CHK // DEPRECATED
  /*  public BigDecimal getBalance(String accNo){
        String sql = "SELECT balance FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        BigDecimal balance;

        try {
            balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class); // see rida annab mulle vastuse andmebaasist
        } catch (EmptyResultDataAccessException e) {
            throw new ApplicationException("Sellist konto numbrit ei ole olemas", e);
        }

        return balance;
    }*/

}
