package ee.bcs.valiit.tasks.BankController;

import java.util.ArrayList;
import java.util.List;

public class BankControllerNotes {

    // HASHMAP - sisuliselt 2 veeruga tabel
    // mis on identifikaator, mille järgi tahame kätte saada -- paneme vasakule poole
    // paremale poole paneme kogu objekti
    // accountide puhul: key on konto nr ja value on objekt
    // vasakul pool konto nr, parema pool konto jääk
    // put kirjutab olemasoleva väärtuse üle
    // saab olla ainult 1 unikaalne key, value'sid võib olla mitu korda
//    List<Account> accountsList = new ArrayList<>();

       /*
    konto info hoidke mapis mitte listis
     */
    // Get accounts list
//    @GetMapping("/accounts")
//    public List<Account> getAccounts(){
//        return accountsList;
//    }


    // Get one account
//    @GetMapping("accounts/{accId}")
//    public Account getOneAccount(@PathVariable("accId") String accId) {
//        return accountsList.get(Integer.parseInt(accId));
//    }

    // Create new account - v1
//    @PostMapping("/accounts")
//    public List<Account> createAccount(@RequestBody Account a){
//        accountsList.add(a);
//        return accountsList;
//    }

//    @PutMapping("/accounts/{accId}")
//    public List<Account> depositMoney (@PathVariable("accId") String accId,
//            @RequestBody Account a){
//        accountsList.add(a);
//        return accountsList;
//    }


//    @DeleteMapping("/accounts")
//    public List<Account> withdrawMoney (@RequestBody Account a){
//        accountsList.add(a);
//        return accountsList;
//    }


}
