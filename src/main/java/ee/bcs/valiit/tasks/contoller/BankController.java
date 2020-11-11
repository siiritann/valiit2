package ee.bcs.valiit.tasks.contoller;

import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.AccountRowMapper;
import ee.bcs.valiit.tasks.BankController.Client;
import ee.bcs.valiit.tasks.BankController.ClientRowMapper;
import ee.bcs.valiit.tasks.service.AccountService;
import ee.bcs.valiit.tasks.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@RestController
public class BankController {

    @Autowired // sellega loon db connectioni ja võtab info application.properites failist
//    private NamedParameterJdbcTemplate jdbcTemplate; // vana meetod enne kihtide eraldamist
    private AccountService accountService; // pärast kihtide splittimist

    @Autowired
    private ClientService clientService;


    // Create new account with balance WITH SQL
    @PostMapping("account")
    public void createAccount(@RequestParam("accNo") String acc_no,
                              @RequestParam("balance") BigInteger balance) {
            accountService.createAccount(acc_no, balance);
    }

    // Get list of accounts WITH SQL
    @GetMapping("accountslist")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }


    // Create new account without balance WITH SQL
    @PutMapping("accountwithoutbalance")
    public void createAccountWithoutBalance(@RequestParam("accNo") String acc_no) {
        accountService.createAccountWithoutBalance(acc_no);
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
        return accountService.depositMoney(accNo, money);
    }


    // withdrawMoney (accountNr, money) WITH SQL
    @PatchMapping("withdrawMoney")
    public String withdrawMoney(@RequestParam("accNo") String accNo,
                                @RequestParam("money") BigDecimal money) {
        return accountService.withdrawMoney(accNo, money);
    }

    // transferMoney WITH SQL
    @PatchMapping("accountTransfer")
    public String transferMoney(@RequestParam("fromAccount") String accNo1,
                                @RequestParam("toAccount") String accNo2,
                                @RequestParam("money") BigDecimal money) {

        return accountService.transferMoney(accNo1, accNo2, money);
    }


    // getAccountBalance (accountNr) FOR ONE WITH SQL
    @GetMapping("account/{accNo}")
    public BigDecimal getAccountBalance(@PathVariable("accNo") String accNo) {
        return accountService.getAccountBalance(accNo);
    }


    // example for multiple rows WITH SQL
    // kutsutakse iga rea kohta välja
    // returnib mulle muutuja, mis on list nendest samadest objektidest mida row mapperis defineerisime
    @GetMapping("accounts/{accNo}")
    public List<Account> selectManyRowsSample(@PathVariable("accNo") String accNo) {
        return accountService.selectManyRowsSample(accNo);
    }


//    @PostMapping("balancehistory/{accNo}/{amount}")
//    public String balancehistory(@PathVariable("accNo") String accNo,
//                                 @PathVariable("amount") BigDecimal amount){
//        accountService.insertBalanceHistory(accNo, amount);
//        return "Balancehistory updated";
//    }


//    @GetMapping("history/{accNo}")
//    public void getHistory(@PathVariable("accNo") String accNo){
//        accountService.getHistory(accNo);
//    }


    //    createClient WITH SQL
    @PutMapping("clients")
    public void createClient(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        clientService.createClient(firstName, lastName);
    }

    // get list of clients
    @GetMapping("clients")
    public List getClientsList() {
        return clientService.getClientsList();
    }


}
