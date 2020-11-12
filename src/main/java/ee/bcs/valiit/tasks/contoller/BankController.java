package ee.bcs.valiit.tasks.contoller;

import ee.bcs.valiit.tasks.BankController.*;
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


    // Get list of accounts WITH SQL
    @GetMapping("accountslist")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }



    // Create new account with CLIENT with balance
    // URL ex: http://localhost:8080/accountforclient?clientId=0&name=kiizu&address=Pärnu%20mnt%20187
        @PostMapping("accountforclient")
        public void createAccountWithClient (@RequestParam("accNo") String accNo,
                                            @RequestParam("amount") BigDecimal amount,
                                            @RequestParam("clientId") int clientId){
            accountService.createAccount(accNo, amount, clientId);
        }

     // Create new account with CLIENT WITHOUT balance

    @PutMapping("accountwithoutbalance")
    public void createAccountWithoutBalance(@RequestParam("accNo") String accNo,
                                            @RequestParam("clientId") int clientId) {
        accountService.createAccountWithoutBalance(accNo, clientId);
    }


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


    @GetMapping("history/{accNo}")
    public List<BalanceHistory> getHistory(@PathVariable("accNo") String accNo){
        return accountService.getHistory(accNo);
    }

// POOLELI
    @GetMapping("historyofaccount/{accNo}")
    public List<TransactionHistory> getHistoryOfAccount(@PathVariable("accNo") String accNo){
        return accountService.getHistoryOfAccount(accNo);
    }



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



    /* DEPRECATED


    1 - Create new account with balance WITH SQL  // DEPRECATED
     siia võib lisada unikaalsuse kontrolli: kui on siis ei luba teha seda, kui on siis luban teha

    @PostMapping("account")
    public void createAccount(@RequestParam("accNo") String acc_no,
                              @RequestParam("balance") BigDecimal balance) {
            accountService.createAccount(acc_no, balance);
    }
   * */

}