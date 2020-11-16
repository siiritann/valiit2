/*
package ee.bcs.valiit.tasks.BankController;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class BankControllerJava {

    List<Client> clientsList = new ArrayList<>();
    private Map<String, Account> accounts = new HashMap<>();

    @Autowired // sellega loon db connectioni ja võtab info application.properites failist
    private NamedParameterJdbcTemplate jdbcTemplate;


    // lihtsam
    //    private Map<String, BigDecimal> accountBalance = new HashMap<>();
    // raskem
//    List<Account> accountsList = new ArrayList<>();

    // Taskid osa 1
    // createAccount (accountNr) // ei valideeri
    // depositMoney (accountNr, money) // sellele kontole lisa raha
    // withdrawMoney (accountNr, money) // sellelt kontolt raha maha, validatsioonid
    // transferMoney (fromAccount, toAccount, money) // validatsioonid
    // getAccountBalance (accNr) // kui palju raha mul on sellel kontol

    // Taskid osa  2
    // createClient(filterName, lastName, ...) //
    // muuda createAccount (accountNr, clientId) // ühel kliendil saab olla mitu kontot
    // getBalanceHistory(accountNr) // kõik tehingud selle kontoga


    // Get list of accounts
    @GetMapping("accountslist")
    public Collection<Account> test(){
        return accounts.values();
    }


    // Create new account
    // loomisel alati on balance null sest konstruktor on selliselt tehtud
    @PostMapping("account")
    public void createAccount(@RequestBody String accNo){
        // lihtsam
//        accountBalance.put(accNo, BigDecimal.ZERO);
        // raskem
        accounts.put(accNo, new Account(accNo));
    }


    // Create new account with CLIENT
    // URL ex: http://localhost:8080/accountforclient?clientId=0&name=kiizu&address=Pärnu%20mnt%20187
    @PostMapping("accountforclient")
    public void createAccountWithClient(@RequestBody String accNo,
                                        @RequestParam int clientId){ // need on RequestParam !
        // kõik asjad mille ees annotatsiooni ei ole, on RequestParam-id
        // kui ma tahan request boydsse panna jsoni siis pean objekti tegema vastavate fieldidega
        // lihtsam
//        accountBalance.put(accNo, BigDecimal.ZERO);
        // raskem
        // siia võib lisada unikaalsuse kontrolli: kui on siis ei luba teha seda, kui on siis luban teha

        Account account = new Account(accNo);
        accounts.put(accNo, account);
//        clientsList.get(clientId).getClientAccounts().add(account); // tekitab errori
//          võib ka tükeldada
        //        Client c = clientsList.get(clientId);
//        c.getClientAccounts().add(account);
    }




    // depositMoney (accNo, money)
    // example: http://localhost:8080/account?accNo=4000&money=1000
    @PutMapping("account")
    public void depositMoney(@RequestParam("accNo") String accNo,
                             @RequestParam("money") BigDecimal money){
        Account account = accounts.get(accNo);
        BigDecimal oldValue = account.getBalance();
        BigDecimal newValue = oldValue.add(money);
        account.setBalance(newValue);
        accounts.put(accNo, account);
//        return "oldValue " + oldValue + ", newValue " + newValue + ", account " + account;
    }


    // withdrawMoney (accountNr, money)
    // example: http://localhost:8080/account?accNo=4000&money=1000
    @PatchMapping("account")
    public String withdrawMoney(@RequestParam("accNo") String accNo,
                                @RequestParam("money") BigDecimal money) {
        Account account = accounts.get(accNo);
        BigDecimal oldValue = account.getBalance();
        if (money.compareTo(oldValue) <= 0) {
            BigDecimal newValue = oldValue.subtract(money);
            account.setBalance(newValue);
            return "Pls, here your money, go buy yourself something";
        } else {
            return "Not enough money";
        }
    }

    // transferMoney (fromAccount, toAccount, money)
    @PatchMapping("accountTransfer")
    public String transferMoney(@RequestParam("fromAccount") String accNo1,
                                @RequestParam("toAccount") String accNo2,
                                @RequestParam("money") BigDecimal money){
        // get fromAccount's balance

        Account fromAccount = accounts.get(accNo1);
        BigDecimal fromAccountBalance = fromAccount.getBalance();
        Account toAccount = accounts.get(accNo2);
        BigDecimal toAccountNewBalance = toAccount.getBalance();

        if (money.compareTo(fromAccountBalance) <= 0) {
            fromAccount.setBalance(fromAccountBalance.subtract(money));
            toAccount.setBalance(toAccountNewBalance.add(money));
            return String.format("Raha kantud kontolt %s kontole %s", accNo1, accNo2);

        } else {
            return  String.format("Kontol fromAccount %s pole piisavalt vahendeid", accNo1);
        }

//        Kas iga kord selle uuesti tegemise asemel saan juba getAccountBalance'it välja kutsuda? - jah

    }




    // getAccountBalance (accountNr)
    @GetMapping("account/{accNo}")
    public BigDecimal getAccountBalance(@PathVariable("accNo") String accNo){
        Account account = accounts.get(accNo);
        return account.getBalance();
    }

    //      getBalanceHistory(accountNr)
    @GetMapping("account/{accId}")
    public void getBalanceHistory(@PathVariable("accId") String accId){

        return ;
    }


*/
/*    @PutMapping("account")
    public void depositMoney(@RequestParam("accountNr") String accountNR,
                             @RequestParam("money")BigDecimal money){
        Map<String, Object> accounts = new HashMap<>();
        accounts.put("EE123",null);
        accounts.put("EE124",null);
        accounts.put("EE125",null);
        accounts.get("EE126");
    }*//*





//    public static void main(String[] args) {
//        Map<String, BigDecimal> accounts = new HashMap<>();
//        accounts.put("EE123", BigDecimal.ZERO);
//        accounts.put("EE124",BigDecimal.TEN);
//        accounts.put("EE125", new BigDecimal("234782934"));
//        System.out.println(accounts.get("EE124")); // mapist on key järgi lihtne parempoolne väärtus kätte saada
//    }


    */
/*  CLIENT REQUEST MAPPINGS
     *//*


//    createClient(firstName lastName, ....)
    @PostMapping("clients")
    public void createClientSQL(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        Client client = new Client(firstName, lastName);
        clientsList.add(client);
        client.setClientId(clientsList.size()); // suurendan clientId-d
    }


    // get list of clients
    @GetMapping("clients")
    public List getClientsList(){
        return clientsList;
    }


}
*/
