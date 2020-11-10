package ee.bcs.valiit.tasks.BankController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@RestController
public class BankController {

    /* DOCUMENTATION

    1 - CREATE ACCOUNT
    2 - CREATE CLIENT
    3 - DEPOSIT MONEY

    * */
    // küsimused
    // 1) kui mul on konstruktor mis ei võta balanssi siis pannakse see nulliks?

    List<Client> clientsList = new ArrayList<>();
    private Map<String, Account> accounts = new HashMap<>();

    @Autowired // sellega loon db connectioni ja võtab info application.properites failist
    private NamedParameterJdbcTemplate jdbcTemplate;


    // Get list of accounts
    @GetMapping("accountslist")
    public Collection<Account> test() {
        return accounts.values();
    }


    // Create new account with balance WITH SQL
    @PostMapping("account")
    public void createAccount(@RequestParam("accNo") String acc_no,
                              @RequestParam("balance") BigInteger balance) {
        String sql = "INSERT INTO account (acc_no, balance) VALUES (:accNo, :balance)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        paramMap.put("balance", balance);
        jdbcTemplate.update(sql, paramMap);
    }

    // Create new account without balance WITH SQL
    @PutMapping("accountwithoutbalance")
    public void createAccountWithoutBalance(@RequestParam("accNo") String acc_no) {
        String sql = "INSERT INTO account (acc_no, balance) VALUES (:acc_no, 0)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accNo", acc_no);
        jdbcTemplate.update(sql, paramMap);
    }

        // Create new account with CLIENT
        // URL ex: http://localhost:8080/accountforclient?clientId=0&name=kiizu&address=Pärnu%20mnt%20187
  /*      @PostMapping("accountforclient")
        public void createAccountWithClient (@RequestBody String accNo,
                                            @RequestParam int clientId,
        String name, String address){ // need on RequestParam !
            // kõik asjad mille ees annotatsiooni ei ole, on RequestParam-id
            // kui ma tahan request boydsse panna jsoni siis pean objekti tegema vastavate fieldidega
            // lihtsam
//        accountBalance.put(accNo, BigDecimal.ZERO);
            // raskem
            // siia võib lisada unikaalsuse kontrolli: kui on siis ei luba teha seda, kui on siis luban teha

            String sql = "INSERT INTO customer (name, address) " +
                    "VALUES (:name, :address)";
//        String sql2 = "INSERT INTO (name, address) VALUES ('" + name + "', :address)"; // see on SQL injection
            Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("name", "Siim");
//        paramMap.put("address", "Tallinn");
            paramMap.put("name", name);
            paramMap.put("address", address);
            jdbcTemplate.update(sql, paramMap);

            Account account = new Account(accNo);
            accounts.put(accNo, account);
//        clientsList.get(clientId).getClientAccounts().add(account); // TODO tekitab errori*/
//          võib ka tükeldada
            //        Client c = clientsList.get(clientId);
//        c.getClientAccounts().add(account);


    // depositMoney (accNo, money) WITH SQL
    @PutMapping("depositMoney")
    public String depositMoney(@RequestParam("accNo") String accNo,
                                @RequestParam("money") BigDecimal money) {

        // PART 1 - get current balance
        String sqlGet = "SELECT balance FROM account WHERE acc_no = :accNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNo", accNo);
        BigDecimal oldValue = jdbcTemplate.queryForObject(sqlGet, paramMap, BigDecimal.class); // see rida annab mulle vastuse andmebaasist

        // PART 2 - update balance
        String sqlSet = "UPDATE account SET balance = :balance WHERE acc_no = :accNo";
        BigDecimal updatedAmount = oldValue.add(money.abs());
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("accNo", accNo);
        paramMap2.put("balance", updatedAmount);
        jdbcTemplate.update(sqlSet, paramMap2);

        return "previous amount was " + oldValue + ", new amount is " + updatedAmount;
    }


        // withdrawMoney (accountNr, money) WITH SQL
        @PatchMapping("withdrawMoney")
        public String withdrawMoney (@RequestParam("accNo") String accNo,
                @RequestParam("money") BigDecimal money){

            // PART 1 - get current balance
            String sqlGet = "SELECT balance FROM account WHERE acc_no = :accNo";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("accNo", accNo);
            BigDecimal oldValue = jdbcTemplate.queryForObject(sqlGet, paramMap, BigDecimal.class);

            // PART 2 - update balance

            if (money.compareTo(oldValue) <= 0) {
                String sqlSet = "UPDATE account SET balance = :balance WHERE acc_no = :accNo";
                BigDecimal newValue = oldValue.subtract(money.abs());
                Map<String, Object> paramMap2 = new HashMap<>();
                paramMap2.put("accNo", accNo);
                paramMap2.put("balance", newValue);
                jdbcTemplate.update(sqlSet, paramMap2);
                return "previous amount was " + oldValue + ", new amount is " + newValue;
            } else {
                return "Not enough money";
            }
        }

        // transferMoney WITH SQL
        @PatchMapping("accountTransfer")
        public String transferMoney(@RequestParam("fromAccount") String accNo1,
                                    @RequestParam("toAccount") String accNo2,
                                    @RequestParam("money") BigDecimal money) {

            // PART 1 - get current balance for both

            // get from Account balance
            String sqlGet1 = "SELECT balance FROM account WHERE acc_no = :accNo1";
            Map<String, Object> paramMapGet1 = new HashMap<>();
            paramMapGet1.put("accNo1", accNo1);
            BigDecimal oldValueFromAccount = jdbcTemplate.queryForObject(sqlGet1, paramMapGet1, BigDecimal.class);

            // get to Account balance
            String sqlGet2 = "SELECT balance FROM account WHERE acc_no = :accNo2";
            Map<String, Object> paramMapGet2 = new HashMap<>();
            paramMapGet2.put("accNo2", accNo2);
            BigDecimal oldValueToAccount = jdbcTemplate.queryForObject(sqlGet2, paramMapGet2, BigDecimal.class);

            // PART 2 - check if possible to make transfer
            // PART 3 - update balance

            if (money.compareTo(oldValueFromAccount) <= 0) {

                // from Account update
                String sqlSet1 = "UPDATE account SET balance = :balance WHERE acc_no = :accNo1";
                BigDecimal newValueFromAccount = oldValueFromAccount.subtract(money.abs());
                Map<String, Object> paramMapSet1 = new HashMap<>();
                paramMapSet1.put("accNo1", accNo1);
                paramMapSet1.put("balance", newValueFromAccount);
                jdbcTemplate.update(sqlSet1, paramMapSet1);

                // to Account update
                String sqlSet2 = "UPDATE account SET balance = :balance WHERE acc_no = :accNo2";
                BigDecimal newValueToAccount = oldValueToAccount.add(money.abs());
                Map<String, Object> paramMapSet2 = new HashMap<>();
                paramMapSet2.put("accNo2", accNo2);
                paramMapSet2.put("balance", newValueToAccount);
                jdbcTemplate.update(sqlSet2, paramMapSet2);


                return "ACCOUNT 1: previous amount was " + oldValueFromAccount + ", new amount is " + newValueFromAccount + "\n" + "ACCOUNT 2: previous amount was " + oldValueToAccount + ", new amount is " + newValueToAccount;
            } else {
                return "Not enough money";
            }
        }


        // getAccountBalance (accountNr)
        @GetMapping("account/{accNo}")
        public BigDecimal getAccountBalance (@PathVariable("accNo") String accNo){
            Account account = accounts.get(accNo);
            return account.getBalance();
        }

//      getBalanceHistory(accountNr)
        @GetMapping("account/{accId}")
        public void getBalanceHistory (@PathVariable("accId") String accId){

            return;
        }


        //    createClient WITH SQL
        @PutMapping("clients")
        public void createClient (@RequestParam("firstName") String firstName,
                @RequestParam("lastName") String lastName){
            String sql = "INSERT INTO client (first_name, last_name) " +
                    "VALUES (:muutuja1, :muutuja2)";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("muutuja1", firstName);
            paramMap.put("muutuja2", lastName);
            jdbcTemplate.update(sql, paramMap);

        }

        // get list of clients
        @GetMapping("clients")
        public List getClientsList () {
            return clientsList;
        }


    }
