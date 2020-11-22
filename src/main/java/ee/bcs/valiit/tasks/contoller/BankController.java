package ee.bcs.valiit.tasks.contoller;

import ee.bcs.valiit.tasks.BankController.*;
import ee.bcs.valiit.tasks.repository.AccountRepository;
import ee.bcs.valiit.tasks.service.AccountService;
import ee.bcs.valiit.tasks.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@CrossOrigin
@RestController
public class BankController {

//    @Autowired // DEPRECATED SEST LÕIME CONSTRUCTORI JUURDE // sellega loon db connectioni ja võtab info application.properites failist
//    private NamedParameterJdbcTemplate jdbcTemplate; // vana meetod enne kihtide eraldamist
    private AccountService accountService; // pärast kihtide splittimist // TODO miks siin ees private on?
    private AccountRepository accountRepository;

    public BankController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Autowired
    private ClientService clientService;


    // Get list of accounts WITH SQL
    @CrossOrigin // seda vaja selleks, et brauseris otse konsoolist fetchida
    @GetMapping("accountslist")
    public Collection<Account> getAccounts() {
        return accountService.getAccounts();
    }



    // Create new account with CLIENT with balance
    // URL ex: http://localhost:8080/accountforclient?clientId=0&name=kiizu&address=Pärnu%20mnt%20187
        @PostMapping("accountforclient")
        public Integer createAccountWithClient (@RequestParam("accNo") String accNo,
                                            @RequestParam("amount") BigDecimal amount,
                                            @RequestParam("clientId") int clientId){
            return accountService.createAccount(accNo, amount, clientId);
        }

     // Create new account with CLIENT WITHOUT balance

    @PutMapping("accountwithoutbalance")
    public void createAccountWithoutBalance(@RequestParam("accNo") String accNo,
                                            @RequestParam("clientId") int clientId) {
        accountService.createAccountWithoutBalance(accNo, clientId);
    }


    // depositMoney (accNo, money) WITH SQL
//    http://localhost:8080/depositMoney?accNo=EE05&money=800
    @CrossOrigin
    @PutMapping("depositMoney")
    public String depositMoney(@RequestParam("accNo") String accNo,
                               @RequestParam("money") BigDecimal money) {
        return accountService.depositMoney(accNo, money);
    }


    // withdrawMoney (accountNr, money) WITH SQL // TODO POOLELI
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

    // GET ACCOUNTS FOR THIS CLIENT
    @GetMapping("accountsforclient/{clientId}")
    public Collection<Account> getAccountsForThisClient(@PathVariable("clientId") int clientId){
        return accountService.getAccountsForThisClient(clientId);
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

    @GetMapping("historyofaccount/{accNo}")
    public List<TransactionHistory> getHistoryOfAccount(@PathVariable("accNo") String accNo){
        return accountService.getHistoryOfAccount(accNo);
    }

    // get balances for all accounts
    @CrossOrigin
    @GetMapping("balances")
    public List<Object> getBalances(){
        return accountService.getBalances();
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